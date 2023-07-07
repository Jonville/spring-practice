package com.tj.edu.practice4.interceptor_filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringInterceptroFilterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringInterceptroFilterApplication.class, args);
    }
}
