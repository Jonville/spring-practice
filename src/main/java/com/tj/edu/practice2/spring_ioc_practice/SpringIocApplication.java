package com.tj.edu.practice2.spring_ioc_practice;

import com.tj.edu.practice2.spring_ioc_practice.ApplicationContextProvider;
import com.tj.edu.practice2.spring_ioc_practice.Encoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringIocApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.tj.edu.practice2.spring_ioc_practice.SpringIocApplication.class, args);
        ApplicationContext ac = ApplicationContextProvider.getApplicationContext();

        Encoder encoder = ac.getBean("urlEncoder2", Encoder.class);

        String url = "www.daum.net";

        System.out.println(" enconder 객체 : " + encoder);

        String resultStr = encoder.encodeString(url);
        byte[] resultByte = encoder.encodeByte(url);

        System.out.println(" String : " + resultStr);

    }
}

@Configuration(enforceUniqueMethods = false)
class AppConfig {

    @Bean("base64Encoder2")
    public Encoder encoder(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }
    @Bean("urlEncoder2")
    public Encoder encoder(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }

}