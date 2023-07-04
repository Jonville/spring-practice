package com.tj.edu.practice2.spring_ioc_practice;

import org.springframework.stereotype.Component;
import com.tj.edu.practice2.spring_ioc_practice.IEncoder;

import java.util.Base64;
import java.util.Base64.Encoder;

@Component
public class Base64Encoder implements IEncoder {

    @Override
    public byte[] encodeByte(String message) {

        // Base64 인코딩
        Encoder encoder = Base64.getEncoder();
        byte[] encoderBytes = encoder.encode(message.getBytes());

//        return Base64.getEncoder().encodeToString(message.getBytes());
        return encoderBytes;
    }

    @Override
    public String encodeString(String message) {
        // Base64 인코딩
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
