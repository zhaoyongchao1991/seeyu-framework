package com.seeyu.lang.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Slf4j
public class AESUtils {

    // 加密算法
    private static String ALGO = "AES";
    private static String ALGO_MODE = "AES/CBC/NoPadding";

    /**
     * 对字符串进行加密的操作
     *
     * @param data
     * @param aKey
     * @param aiv
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String aKey, String aiv) {
        try {
            byte[] encrypted = encrypt(data.getBytes(), aKey, aiv);
            return Base64Utils.encode(encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对数据进行解密， 解密目标是字符串
     *
     * @param encryptedData base64加密过后的字符串
     * @param aKey
     * @param aiv
     * @return
     */
    public static String decrypt(String encryptedData, String aKey, String aiv) {
        try {
            byte[] encrypted = Base64Utils.decode(encryptedData);
            byte[] original = decrypt(encrypted, aKey, aiv);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用来进行加密的操作
     *
     * @param data
     * @param aKey
     * @param aiv
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String aKey, String aiv){
        try {
            Cipher cipher = Cipher.getInstance(ALGO_MODE);
            int blockSize = cipher.getBlockSize();
            int plaintextLength = data.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(data, 0, plaintext, 0, data.length);
            SecretKeySpec keyspec = new SecretKeySpec(aKey.getBytes(StandardCharsets.UTF_8), ALGO);
            IvParameterSpec ivspec = new IvParameterSpec(aiv.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            return cipher.doFinal(plaintext);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用来进行解密的操作
     *
     * @param data
     * @param aKey
     * @param aiv
     * @return
     */
    public static byte[] decrypt(byte[] data, String aKey, String aiv) {
        try {
            Cipher cipher = Cipher.getInstance(ALGO_MODE);
            SecretKeySpec keyspec = new SecretKeySpec(aKey.getBytes(StandardCharsets.UTF_8), ALGO);
            IvParameterSpec ivspec = new IvParameterSpec(aiv.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}