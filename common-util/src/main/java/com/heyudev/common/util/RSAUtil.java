package com.heyudev.common.util;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * rsa
 * @author heyudev
 * @date 2019/06/11
 */
public class RSAUtil {

    /**
     * RSA最大加密明文大小 2048
     */
    private static final int MAX_ENCRYPT_BLOCK = 245;

    /**
     * RSA最大解密密文大小 2048
     */
    private static final int MAX_DECRYPT_BLOCK = 256;

    public static final String KEY_ALGORITHM = "RSA";
    private static KeyFactory keyFactory = null;

    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnGFRhWCoq6+yhfmxlS36Nm2HnE3/c1eFqHRlX9VPf270u9VcZLCR724kXe432sb8MrbHJzKsrzs7jGpoYlJ8j/p1sgLjKH7xjbczt4dpCqSsgRH/yDXQ9XcDfCDAaE5vFB1TAZHzEhRkqJyvll22X2mfCIVTkE5I/ReJMuGUJM2SF69/LZjQRRvUn1ZKNh84zo+BFxAXjixB6EZdfwDHc6XBsEI8+J4ed7JPeqpYN1uA0uBuLdwT0C7gY5/n+HeuaNTsI0akwfyfBHzX0Z+SFIA31V2Ek6RmSQTubkXRRZGbotytdzM4Tf9UzLzdmCP/GjcPUXe9+eUIcR1VUws2WQIDAQAB";
    private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCcYVGFYKirr7KF+bGVLfo2bYecTf9zV4WodGVf1U9/bvS71VxksJHvbiRd7jfaxvwytscnMqyvOzuMamhiUnyP+nWyAuMofvGNtzO3h2kKpKyBEf/INdD1dwN8IMBoTm8UHVMBkfMSFGSonK+WXbZfaZ8IhVOQTkj9F4ky4ZQkzZIXr38tmNBFG9SfVko2HzjOj4EXEBeOLEHoRl1/AMdzpcGwQjz4nh53sk96qlg3W4DS4G4t3BPQLuBjn+f4d65o1OwjRqTB/J8EfNfRn5IUgDfVXYSTpGZJBO5uRdFFkZui3K13MzhN/1TMvN2YI/8aNw9Rd7355QhxHVVTCzZZAgMBAAECggEAej59OpVv5R4ooSCq2GIjqa5/hwlV+rVdT20F3h9i0MxM1hSY8EEzI4bnOOJCmWxZs8876YFsimL0TvTTeqDerLJulOQ67OonK9Adxoc87swB6bfHSpv/KBOMDtmPbzp5DLFLtPuBlw2IDmLFw/an3Pg1ELUguLrpxadDYhoC86ze1YCL3sdYcP5j5DAGbJhqjEN1Lg4TlsaFMT5ScQ8440n8r98sEXKk+luAwIZajWyh8inaTnmSYd1/ep2/xiKLAxAIDpAB3MG5MxDdMQdQHpIMDWQ9qx2cqc1FUHJJ1a3OC4rsVjileQMOA67EpY2EDPWHLYrty4O1IePCBPUNgQKBgQDpT8glLSdNYeKVPQ2QlZZTM9urjKt+TV6BeFhEHImryp7yFU/j5x8iw5KyUxSr/9gEY34YPzmDkD91qxNUESupxHn2T5QSUKVCLVLSt6uAN/wmgq5wq2N4kA63VWowSv6L20h+ZOm19pYpQijIw7LmxrjZnUFkrShmgNzKYL9TUQKBgQCrlloQtFkwnZ8hqLY8WY77hIQQ22ww2iKlPWh4vW/WdGENKy9fC++Sqs0+Qw64E2ZJcm2kN9dfSYZnW3v9p7GhgQbH14FCgJgWtDmYHi7tqzwiE12jA4kkF69MLkWeRUaqDfD/jzoBS9mll/8UdqXTOHn6BlUA6oJBMvZX2AWgiQKBgDK4JFNooH2jBOXakuNWkYpe4KdHelSHrz7IEbAk4AzXjHwmA+sQaGVfTLm1c+38PVKe6l+NBwzBw/npidchsHNEghr4q8DbhUGAptumEANcqFUa3Z8iswoXjwtt5xq+gt2Wewj3GRN0mqo3drRGTtL6al7sX7XqKrk11EmuhIkxAoGAKgLf/mMJy1tsUONr6w7KcU6avz3aGYPvI6ILaDSJo2pPMNg3+v+DItfq9KmaevtU+fqoWaLKeqwX2kqN6ycZ2L8sl9Vt8ohltHlf+Jy7RLm+OWGgdX8yHsIly3Mw3HV9vbk4HnpE63hUy7SRYcNDCpozU0IlodnJFomRwyRDDHECgYEAqDSodUaEOlh1I8u5dmPlkR6zvqIS0m/f4q3fRCSENOB3bKP4hAfS4F1B/CK1NkaGMU2EXBOS11aMlirGI1NGS0hsQVn4++9rpd+eOsOhbpv6+FkL+VQllFlxAj0UdSKzr5bWLyuLx5qRhX/oFihBoOLKBvmp8tmZQV6UehzWiJA=";

    static {
        try {
            keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 解密方法
     *
     * @param dataStr 要解密的数据
     * @return 解密后的原数据
     * @throws Exception
     */
    public static String decrypt(String dataStr) throws Exception {
        //要加密的数据
        System.out.println("要解密的数据:" + dataStr);
        //对私钥解密
        Key decodePrivateKey = getPrivateKeyFromBase64KeyEncodeStr(PRIVATE_KEY);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, decodePrivateKey);
        byte[] encodedData = Base64.getDecoder().decode(dataStr);
        byte[] decodedData = cipher.doFinal(encodedData);
        String decodedDataStr = new String(decodedData, StandardCharsets.UTF_8);
        System.out.println("私钥解密后的数据:" + decodedDataStr);
        return decodedDataStr;
    }

    public static Key getPrivateKeyFromBase64KeyEncodeStr(String keyStr) {
        byte[] keyBytes = Base64.getDecoder().decode(keyStr);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        Key privateKey = null;
        try {
            privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return privateKey;
    }

    /**
     * 获取base64加密后的字符串的原始公钥
     *
     * @param keyStr
     * @return
     */
    public static Key getPublicKeyFromBase64KeyEncodeStr(String keyStr) {
        byte[] keyBytes = Base64.getDecoder().decode(keyStr);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        Key publicKey = null;
        try {
            publicKey = keyFactory.generatePublic(x509KeySpec);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return publicKey;
    }

    /**
     * 公钥加密方法
     *
     * @param dataStr 要加密的数据
     * @param dataStr 公钥base64字符串
     * @return 加密后的base64字符串
     * @throws Exception
     */
    public static String encryptPublicKey(String dataStr) throws Exception {
        //要加密的数据
        System.out.println("要加密的数据:" + dataStr);
        byte[] data = dataStr.getBytes();
        // 对公钥解密
        Key decodePublicKey = getPublicKeyFromBase64KeyEncodeStr(PUBLIC_KEY);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, decodePublicKey);
        byte[] encodedData = cipher.doFinal(data);
        String encodedDataStr = Base64.getEncoder().encodeToString(encodedData);
        System.out.println("公钥加密后的数据:" + encodedDataStr);
        return encodedDataStr;
    }

    /**
     * 使用公钥进行分段加密
     *
     * @param dataStr 要加密的数据
     * @return 公钥base64字符串
     * @throws Exception
     */
    public static String encryptByPublicKey(String dataStr)
            throws Exception {
        //要加密的数据
        System.out.println("要加密的数据:" + dataStr);
        byte[] data = dataStr.getBytes("UTF-8");
        // 对公钥解密
        Key decodePublicKey = getPublicKeyFromBase64KeyEncodeStr(PUBLIC_KEY);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, decodePublicKey);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        String encodedDataStr = Base64.getEncoder().encodeToString(encryptedData);
        System.out.println("公钥加密后的数据:" + encodedDataStr);
        return encodedDataStr;
    }

    /**
     * 使用私钥进行分段解密
     *
     * @param dataStr 使用base64处理过的密文
     * @return 解密后的数据
     * @throws Exception
     */
    public static String decryptByPrivateKey(String dataStr) throws Exception {
        byte[] encryptedData = Base64.getDecoder().decode(dataStr);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key decodePrivateKey = getPrivateKeyFromBase64KeyEncodeStr(PRIVATE_KEY);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, decodePrivateKey);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        String decodedDataStr = new String(decryptedData, StandardCharsets.UTF_8);
        System.out.println("私钥解密后的数据:" + decodedDataStr);
        return decodedDataStr;
    }

    public static void main(String[] args) throws Exception {
        String content = "testtesttesttest";
        System.out.println("加密前：" + content);
        long start = System.currentTimeMillis();
        String encrypt = encryptByPublicKey(content);
        System.out.println("encryptByPublicKey time = " + (System.currentTimeMillis() - start));
        System.out.println(encrypt.length() + ":加密后：" + encrypt);
        long start1 = System.currentTimeMillis();
        String decrypt = decryptByPrivateKey(encrypt);
        System.out.println("decryptByPrivateKey time = " + (System.currentTimeMillis() - start1));
        System.out.println("解密后：" + decrypt);
    }
}
