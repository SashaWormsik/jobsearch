package org.chervyakovsky.jobsearch.util;


import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncryptor {

    private PasswordEncryptor() {
    }

    public static String encrypt(String password) {
        return DigestUtils.md5Hex(password);
    }

    public static boolean comparePassword(String password, String passwordFromDb) {
        return passwordFromDb.equals(encrypt(password));
    }
}
