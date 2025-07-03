package com.github.eventure.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Encryption {
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 4096;

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static byte[] generateHash(String contents, byte[] salt) {
        try {
            // PBKDF2 Key Spec
            KeySpec spec = new PBEKeySpec(contents.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
            var factory = SecretKeyFactory.getInstance(ALGORITHM);

            // Generate hash
            return factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            System.out.println(e);
            return null;
        }
    }

    public static boolean checkHashes(byte[] hash1, byte[] hash2) {
        return Arrays.equals(hash1, hash2);
    }
}