package com.foo.jwt.service.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author foo
 * @since 2022-06-05
 */
public final class CodeUtil {

    public static String generateCode() {
        String code;
        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            int c = random.nextInt(9000) + 1000;
            code = String.valueOf(c);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("no such algorithm");
        }
        return code;
    }

}
