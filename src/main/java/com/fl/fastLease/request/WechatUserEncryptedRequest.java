package com.fl.fastLease.request;

import lombok.Getter;
import lombok.Setter;

/**
 * &#064;Description
 * &#064;Author Evan
 * &#064;Date 2025/2/19 23:42
 */
@Setter
@Getter
public class WechatUserEncryptedRequest {


    private String sessionKey;

    private String encryptedData;

    private String iv;

}
