package com.fl.fastLease.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * &#064;Description 用户信息
 * &#064;Author Evan
 * &#064;Date 2025/2/17 21:32
 */
@Data
public class FlUser {

    private Long id;

    /** 用户在小程序唯一ID*/
    private String openId;

    /** 唯一标识符*/
    private String unionId;

    /** 用户手机号码*/
    private String phone;

    /** 用户邮箱*/
    private String email;

    /** 用户昵称 如果没有则默认生成一个昵称*/
    private String nikeName;

    /** 用户在微信的头像*/
    private String image;

    /** 用户性别  0-未知  1-男 2-女*/
    private Integer gender;

    /** 用户所属国家*/
    private String country;

    /** 用户所属省份*/
    private String province;

    /** 用户所属城市*/
    private String city;

    /** 用户创建时间*/
    private LocalDateTime createTime;



}
