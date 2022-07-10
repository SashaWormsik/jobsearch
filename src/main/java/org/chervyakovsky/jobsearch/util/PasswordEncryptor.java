package org.chervyakovsky.jobsearch.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * The PasswordEncryptor util class.
 * Perform password encryption using md5Hex algorithm.
 */
public class PasswordEncryptor {

    private PasswordEncryptor() {
    }

    /**
     * Encrypt password using md5Hex algorithm.
     *
     * @param password the password string.
     * @return the encrypted password.
     */
    public static String encrypt(String password) {
        return DigestUtils.md5Hex(password);
    }

    /**
     * Encrypt password using md5Hex algorithm.
     *
     * @param password       password for comparison from the request.
     * @param passwordFromDb the password from the database.
     * @return the result of comparing two passwords.
     */
    public static boolean comparePassword(String password, String passwordFromDb) {
        return passwordFromDb.equals(encrypt(password));
    }
}
