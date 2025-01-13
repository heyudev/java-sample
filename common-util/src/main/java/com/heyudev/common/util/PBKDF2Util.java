package com.heyudev.common.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PBKDF2Util {
    public static byte[] generateSalt(int length) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[length];
        random.nextBytes(salt);
        return salt;
    }

    public static byte[] generateKey(char[] password, byte[] salt, int iterations, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength * 8); // keyLength 单位是 bit
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256"); // 使用 HMAC-SHA256 作为 PRF
        return factory.generateSecret(spec).getEncoded();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = "mysecretpassword";
        byte[] salt = generateSalt(16); // 16 字节 salt
        int iterations = 100000; // 迭代次数，建议至少 10000
        int keyLength = 256; // 密钥长度（bits）

        byte[] key = generateKey(password.toCharArray(), salt, iterations, keyLength);

        System.out.println("Salt: " + Base64.getEncoder().encodeToString(salt));
        System.out.println("Derived Key: " + Base64.getEncoder().encodeToString(key));

        // 将 salt、iterations 和 derived key 存储起来，用于后续的验证
    }
}
