package com.example.encryption.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public class AESUtil {

    private static final String AES = "AES";

    public static byte[] generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance(AES);
        keygen.init(256); // Using 256-bit AES
        SecretKey key = keygen.generateKey();
        return key.getEncoded();
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] encryptedData, byte[] key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(encryptedData);
    }
}