package com.heyudev.common.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * aes
 * @author heyudev
 * @date 2019/06/10
 */
public class AESUtil {
    /**
     * 16位Key
     */
    private static final String KEY = "taskmistresses";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    public static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] base64Decode(String base64Code) throws Exception {
        return Base64.getDecoder().decode(base64Code);
    }

    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator gen = KeyGenerator.getInstance("AES");
        gen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator gen = KeyGenerator.getInstance("AES");
        gen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    /**
     * 测试 *
     */
    public static void main(String[] args) throws Exception {
//        edb005f7a3ef440492f546c88590b99e
        String content = "asdasdasd";
        System.out.println("加密前：" + content);
        System.out.println("加密密钥和解密密钥：" + KEY);
        long start = System.currentTimeMillis();
        String encrypt = aesEncrypt(content, KEY);
        System.out.println("aesEncrypt time = " + (System.currentTimeMillis() - start));
        System.out.println(encrypt.length() + ":加密后：" + encrypt);
        long start1 = System.currentTimeMillis();
        String decrypt = aesDecrypt(encrypt, KEY);
        System.out.println("aesDecrypt time = " + (System.currentTimeMillis() - start1));
        System.out.println("解密后：" + decrypt);
    }
}
