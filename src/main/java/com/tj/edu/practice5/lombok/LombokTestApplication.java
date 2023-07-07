package com.tj.edu.practice5.lombok;

import com.tj.edu.practice5.lombok.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LombokTestApplication {
    public static void main(String[] args) {

        User user = new User();
        user.setName("홍길동");
        user.setEmail("agildong@gmail.com");

        User user2 = new User("김복순" , "abc123@gmail.com" , null , null);

        System.out.println(user.getName());
        System.out.println(user.getEmail());

        System.out.println(user);
        System.out.println(user2);

    }
}
