package com.example.encryption.service;

import com.example.encryption.util.AESUtil;
import com.example.encryption.util.RSAUtil;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import java.util.Base64;
//import javax.annotation.PostConstruct;
import java.security.KeyPair;

@Service
public class EncryptionService {

    private KeyPair rsaKeyPair;

    @PostConstruct
    public void init() throws Exception {
        // Generate RSA key pair
        rsaKeyPair = RSAUtil.generateRSAKeyPair();
    }

    public String encryptData(String jsonData) throws Exception {
        // Generate AES key
        byte[] aesKey = AESUtil.generateAESKey();

        // Encrypt data with AES key
        byte[] encryptedData = AESUtil.encrypt(jsonData.getBytes("UTF-8"), aesKey);

        // Encrypt AES key with RSA public key
        byte[] encryptedAesKey = RSAUtil.encrypt(aesKey, rsaKeyPair.getPublic());

        // Combine encrypted data and encrypted AES key
        String encryptedDataStr = Base64.getEncoder().encodeToString(encryptedData);
        String encryptedAesKeyStr = Base64.getEncoder().encodeToString(encryptedAesKey);

        // Return as JSON string
        return "{ \"encryptedData\": \"" + encryptedDataStr + "\", \"encryptedKey\": \"" + encryptedAesKeyStr + "\" }";
    }

    public String decryptData(String encryptedJson) throws Exception {
        // Parse JSON to extract encrypted data and key
        // For simplicity, using basic parsing
        String encryptedDataStr = encryptedJson.split("\"encryptedData\": \"")[1].split("\"")[0];
        String encryptedAesKeyStr = encryptedJson.split("\"encryptedKey\": \"")[1].split("\"")[0];

        byte[] encryptedData = Base64.getDecoder().decode(encryptedDataStr);
        byte[] encryptedAesKey = Base64.getDecoder().decode(encryptedAesKeyStr);

        // Decrypt AES key with RSA private key
        byte[] aesKey = RSAUtil.decrypt(encryptedAesKey, rsaKeyPair.getPrivate());

        // Decrypt data with AES key
        byte[] decryptedData = AESUtil.decrypt(encryptedData, aesKey);

        return new String(decryptedData, "UTF-8");
    }
}