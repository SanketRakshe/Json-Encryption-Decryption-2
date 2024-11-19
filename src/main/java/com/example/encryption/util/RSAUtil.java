package com.example.encryption.util;

import javax.crypto.Cipher;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {

    private static final String RSA = "RSA";

    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(RSA);
        keygen.initialize(2048); // Using 2048-bit RSA
        return keygen.generateKeyPair();
    }
    
    
    public static void saveKeyToFile(String filePath, byte[] key) throws Exception {
        Files.createDirectories(Paths.get(filePath).getParent());
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(key);
        }
    }

    public static PublicKey loadPublicKey(byte[] data) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePublic(new X509EncodedKeySpec(data));
    }

    public static PrivateKey loadPrivateKey(byte[] data) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(data));
    }

    public static byte[] encrypt(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }
}