package com.example.encryption.controller;

import com.example.encryption.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EncryptionController {

    @Autowired
    private EncryptionService encryptionService;

    @PostMapping(value = "/encrypt", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String encryptData(@RequestBody String jsonData) throws Exception {
        return encryptionService.encryptData(jsonData);
    }

    @PostMapping(value = "/decrypt", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String decryptData(@RequestBody String encryptedData) throws Exception {
        return encryptionService.decryptData(encryptedData);
    }
}