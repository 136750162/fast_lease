package com.fl.fastLease.service.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.IdUtil;
import com.fl.fastLease.common.entity.ResultEntity;
import com.fl.fastLease.common.util.RedisCache;
import com.fl.fastLease.common.util.SecurityUtil;
import com.fl.fastLease.common.util.WechatUtil;
import com.fl.fastLease.mapper.FlUserMapper;
import com.fl.fastLease.model.FlUser;
import com.fl.fastLease.request.WechatUserEncryptedRequest;
import com.fl.fastLease.service.IFlUserService;
import com.fl.fastLease.vo.FlUserVO;
import com.fl.fastLease.vo.WechatSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * &#064;Description
 * &#064;Author Evan
 * &#064;Date 2025/2/17 22:30
 */
@Service
@Log4j2
public class FlUserServiceImpl implements IFlUserService {

    private static final Integer EXPIRED_TIME = 1000 * 60 *60 * 24;

    private static final String USER_CACHE_KEY = "fl:user:loginInfo";

    @Autowired
    private FlUserMapper flUserMapper;

    @Autowired
    private WechatUtil wechatUtil;

    @Autowired
    private RedisCache redisCache;


    @Override
    public ResultEntity<FlUserVO> onLogin(String code) {
//        if (log.isDebugEnabled()) {
//            log.debug("User login login code is :{}", code);
//        }
        // 调用微信登录接口
        WechatSession login = wechatUtil.login(code);
        // 校验用户是否存在，如果不存在则生成用户信息
        FlUser user = flUserMapper.getUser(login.getOpenId());
        FlUserVO resultUser = new FlUserVO();
        int isNewUser = 0;
        if (user == null){
            // 生成用户信息
            user = new FlUser();
            user.setOpenId(login.getOpenId());
            user.setNikeName("Fast_"+ IdUtil.fastUUID());
            user.setGender(0);
            user.setImage("");
            user.setCreateTime(LocalDateTime.now());
            flUserMapper.insertOrUpdate(user);
            isNewUser = 1;
        }
        resultUser.setIsNewUser(isNewUser);
        // 设置用户
        resultUser.setNikeName(user.getNikeName());
        resultUser.setCity(user.getCity());
        resultUser.setGender(user.getGender());
        resultUser.setImage(user.getImage());
        resultUser.setExpired(EXPIRED_TIME);
        resultUser.setId(user.getId());
        // 生成用户新的签名秘钥
        String sessionKey = login.getSessionKey();
        String newSession = SecurityUtil.encrypt(sessionKey);
        resultUser.setSessionKey(newSession);
        // 将用户登录凭证存储到Redis中，并且返回用户信息
        redisCache.setCacheObject(String.join("", USER_CACHE_KEY, sessionKey), resultUser, EXPIRED_TIME, TimeUnit.MILLISECONDS);
        return ResultEntity.success(resultUser);
    }



    @Override
    public ResultEntity<FlUserVO> checkLogin(String sessionKey) {
        FlUserVO resultUser = getCacheUser(sessionKey);
        if (resultUser == null){ return  ResultEntity.error(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value(), "当前用户暂未登录，请重新登录");}
        return ResultEntity.success(resultUser);
    }

    @Override
    public ResultEntity<Object> decryptUserData(WechatUserEncryptedRequest request) {
        // 根据用户传输过来的sessionKey 查询缓存登录状态
        String sessionKey = request.getSessionKey();
        String wechatSessionKey = SecurityUtil.decrypt(sessionKey);
        FlUserVO cacheUser = getCacheUser(sessionKey);
        // 开始解密数据
        try {
            String userInfoJson = wechatUtil.decryptUserInfo(wechatSessionKey, request.getEncryptedData(), request.getIv());
            // 解析数据，将数据转化为业务数据入库
            FlUser user = new FlUser();
            user.setId(cacheUser.getId());
            flUserMapper.update(user);
        } catch (Exception e) {
            log.error("解密Wechat用户数据失败，失败的原因为:{}", ExceptionUtil.stacktraceToOneLineString(e, -1));
            return ResultEntity.error(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return ResultEntity.success();
    }



    @Override
    public FlUser getUser(String openId) {
        return flUserMapper.getUser(openId);
    }

    @Override
    public int insertOrUpdate(FlUser user) {

        return flUserMapper.insertOrUpdate(user);
    }

    private  FlUserVO getCacheUser(String sessionKey){
        return redisCache.getCacheObject(String.join("", USER_CACHE_KEY, SecurityUtil.decrypt(sessionKey)));
    }
}
