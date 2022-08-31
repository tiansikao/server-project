package com.zyg.me.commons;

import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Utils {
    /**
     * 生成md5
     * @param str
     * @return
     */
    public static String getMD5(String str,String slat) {
        String base = str +"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
