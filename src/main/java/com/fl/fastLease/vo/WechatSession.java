package com.fl.fastLease.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * &#064;Description 微信登录Session类
 * &#064;Author Evan
 * &#064;Date 2025/2/17 22:50
 */
@Setter
@Getter
public class WechatSession {

    /** 用户唯一ID标识*/
    @JsonProperty("openid")
    private  String openId;

    /** 用户会话Key*/
    @JsonProperty("session_key")
    private String sessionKey;

    /** 采用Union 认证时触发此字段返回*/
    @JsonProperty("unionid")
    private String unionId;
}
