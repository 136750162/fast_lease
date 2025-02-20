package com.fl.fastLease.common.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fl.fastLease.vo.WechatSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * &#064;Description 微信工具类
 * &#064;Author Evan
 * &#064;Date 2025/2/17 22:48
 */
@Component
public class WechatUtil {

    @Value("${wechat.loginUrl: https://api.weixin.qq.com/sns/jscode2session}")
    private String wechatLoginUrl;

    private static final String APPID = "wxe2af8edafa3c77bf";

    private static final String SECRET = "4d65cf6714aeb006578038484072e8b1";



    //获取凭证校检接口
    public  WechatSession login(String code) {
        //微信的接口
        String url =  String.join("", wechatLoginUrl, "?appid=", APPID, "&secret=", SECRET, "&js_code=", code, "&grant_type=authorization_code");
        RestTemplate restTemplate = new RestTemplate();
        //进行网络请求,访问url接口
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //根据返回值进行后续操作
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            String sessionData = responseEntity.getBody();
            //解析从微信服务器获得的openid和session_key;
            return JSON.parseObject(sessionData, WechatSession.class);
        }
        return null;
    }

    public String decryptUserInfo(String sessionKey, String encryptData, String iv) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] sessionKeyDecoder = decoder.decode(sessionKey);
        byte[] encryptDataDecoder = decoder.decode(encryptData);
        byte[] ivDecoder = decoder.decode(iv);
        SecretKey secretKeySpec  = new SecretKeySpec(sessionKeyDecoder, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivDecoder);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        // 解密数据
        byte[] decryptedBytes = cipher.doFinal(encryptDataDecoder);
        String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);
        decryptedText = unpad(decryptedText);
        // 验证APPID
        JSONObject jsonObject = JSON.parseObject(decryptedText);
        String appId = jsonObject.getJSONObject("watermark").getString("appid");
        if (!APPID.equals(appId)) {
            throw new Exception("Invalid Buffer");
        }
        return decryptedText;
    }

    private String unpad(String s) {
        int padLength = s.charAt(s.length() - 1);
        return s.substring(0, s.length() - padLength);
    }

}
