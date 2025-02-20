package com.fl.fastLease.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * &#064;Description 登录用户返回信息
 * &#064;Author Evan
 * &#064;Date 2025/2/18 23:54
 */
@Data
public class FlUserVO implements Serializable {

    private Long id;

    /**用户昵称 */
    private String nikeName;

    /** 性别 0-未知  1-男 2-女*/
    private Integer gender;

    /** 用户头像 */
    private String image;

    /** 城市*/
    private String city;

    /** 访问SessionKey*/
    private String sessionKey;

    /** 过期时间*/
    private Integer expired;

    /** 是否是新用户*/
    private Integer isNewUser;

}
