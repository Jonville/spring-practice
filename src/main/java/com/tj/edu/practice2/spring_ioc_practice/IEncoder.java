package com.tj.edu.practice2.spring_ioc_practice;

public interface IEncoder {

    String encodeString(String message);

    byte[] encodeByte(String message);

}
