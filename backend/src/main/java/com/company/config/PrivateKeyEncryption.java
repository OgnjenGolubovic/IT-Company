package com.company.config;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
@Component
public class PrivateKeyEncryption {
    public String encryptToString(String plaintext){
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey());
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    public String decryptFromString(String encryptedString){
        try{
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedString);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        }catch (Exception ex){
            return ex.getMessage();
        }
    }
    public SecretKey getPrivateKey() {
        String keyFilePath = "src/main/resources/keys/privateKey.key";

        try {
            byte[] encodedKey = Files.readAllBytes(Paths.get(keyFilePath));
            return new SecretKeySpec(encodedKey, "AES");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
 