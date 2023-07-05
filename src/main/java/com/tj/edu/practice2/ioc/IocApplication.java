package com.tj.edu.practice2.ioc;

import java.util.Arrays;

public class IocApplication {
    public static void main(String[] args) {
        // IoC (Inversion of Control) (제어의 역전) -> 클래스 생성 주입에 관한 내용

        String url = "www.naver.com";

//      String result =  Base64Encoder.encodeString(url);
//      String result = new UrlEncoder().encodeString(url);

//        Encoder encoder = new Encoder(new Base64Encoder());
//        String result = encoder.base64Encode(url);

          Encoder encoder = new Encoder(new Base64Encoder());
          String resultStr = encoder.encodeString(url);
          byte[] resultByte = encoder.encodeByte(url);


        System.out.println("뭐가나올까아 String-> " + resultStr);
        System.out.println("뭐가나올까아 Byte -> " + Arrays.toString(resultByte));


    }
}
