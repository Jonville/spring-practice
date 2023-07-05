package com.tj.edu.practice2.spring_ioc;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringIocApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringIocApplication.class, args);
        ApplicationContext ac = ApplicationContextProvider.getApplicationContext();

        Encoder encoder = ac.getBean("encoder2", Encoder.class);

        String url = "www.naver.com";

        System.out.println(" enconder 객체 : " + encoder);

        String resultStr = encoder.encodeString(url);
        byte[] resultByte = encoder.encodeByte(url);

        System.out.println("뭔가 나오나 String : " + resultStr);

        System.out.println("Spring-Ioc 연습");
    }
}

@Configuration(enforceUniqueMethods = false)
class AppConfig {

    @Bean
    public Encoder encoder1(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }

    @Bean
    public Encoder encoder2(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }
}