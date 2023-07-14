package com.tj.edu.practice6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpaQueryMethodTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaQueryMethodTestApplication.class, args);

    }

}
