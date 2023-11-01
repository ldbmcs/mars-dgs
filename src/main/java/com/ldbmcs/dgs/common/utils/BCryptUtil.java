package com.ldbmcs.dgs.common.utils;


import cn.hutool.crypto.digest.BCrypt;

public final class BCryptUtil {
    private BCryptUtil() {
    }

    public static String encode(String password) {
        return BCrypt.hashpw(password);
    }

    public static boolean checkpw(String plaintext, String hashed) {
        return BCrypt.checkpw(plaintext, hashed);
    }
}
