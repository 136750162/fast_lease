package com.fl.fastLease.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * &#064;Description 微信登录请求对象
 * &#064;Author Evan
 * &#064;Date 2025/2/18 20:48
 */
@Setter
@Getter
public class WechatLoginRequest implements Serializable {

    /** 小程序唯一标识*/
    @JsonProperty("openid")
    private String openId;

    /** 小程序的 app secret*/
    @JsonProperty("secret")
    private String secret;

    /** 登录时获取的 code*/
    @JsonProperty("js_code")
    private String jsCode;

    /** 填写为 默认填写authorization_code*/
    @JsonProperty("grant_type")
    private String grantType ;

}
