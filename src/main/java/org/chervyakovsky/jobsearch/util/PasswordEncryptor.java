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

//    public static void main(String[] args) {
//        String password = "sasha123456";
//        String login = "sashaWorms";
//        System.out.println(encrypt(password));
    // f78a2a492c15883fa66850a27e273718
//    }
}
