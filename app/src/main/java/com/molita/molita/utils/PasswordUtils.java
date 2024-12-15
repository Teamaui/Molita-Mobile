package com.molita.molita.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    public static String hashPassword(String password) {
        try {
            // Buat instance MessageDigest dengan algoritma SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Hash password
            byte[] hashedPassword = md.digest(password.getBytes());
            // Konversi byte array ke format hex string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifyPassword(String inputPassword, String storedHash) {
        String inputHash = hashPassword(inputPassword);
        return MessageDigest.isEqual(inputHash.getBytes(), storedHash.getBytes());
    }

}
