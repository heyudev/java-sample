package com.heyudev.common.util;

import javax.crypto.KeyAgreement;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.util.Base64;

public class DiffieHellmanUtil {

    public static void main(String[] args) throws Exception {
        // 1. 生成 Diffie-Hellman 密钥对
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DiffieHellman");
//        kpg.initialize(2048); // 密钥长度，越大越安全，但计算更慢
        kpg.initialize(512); // 密钥长度，越大越安全，但计算更慢
        KeyPair aliceKeyPair = kpg.generateKeyPair();
        KeyPair bobKeyPair = kpg.generateKeyPair();

        // 2. 密钥交换
        PublicKey alicePublicKey = aliceKeyPair.getPublic();
        PublicKey bobPublicKey = bobKeyPair.getPublic();

        // 3. 计算共享密钥
        KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("DiffieHellman");
        aliceKeyAgree.init(aliceKeyPair.getPrivate());
        aliceKeyAgree.doPhase(bobPublicKey, true);
        byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();

        KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DiffieHellman");
        bobKeyAgree.init(bobKeyPair.getPrivate());
        bobKeyAgree.doPhase(alicePublicKey, true);
        byte[] bobSharedSecret = bobKeyAgree.generateSecret();

        // 4. 验证共享密钥是否相同
        System.out.println("Alice's shared secret: " + Base64.getEncoder().encodeToString(aliceSharedSecret));
        System.out.println("Bob's shared secret: " + Base64.getEncoder().encodeToString(bobSharedSecret));
    }
}
