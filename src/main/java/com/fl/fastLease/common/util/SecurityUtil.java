package com.fl.fastLease.common.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * &#064;Description
 * &#064;Author Evan
 * &#064;Date 2025/2/19 23:36
 */
public class SecurityUtil {
    static final AES AES = SecureUtil.aes(SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded());

    public static String encrypt(String key){
       return AES.encryptHex(key);
    }

    public static String decrypt(String key){
        return AES.decryptStr(key);
    }
}
