package com.tj.edu.practice4.aop.controller;

import com.tj.edu.practice4.aop.annotations.TimeChecker;
import com.tj.edu.practice4.aop.dto.User;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    // 사용자 정보를 조회하는 api
    @TimeChecker
    @GetMapping("/user/{id}")
    public String user(
            @PathVariable(name = "id") String id1 ,
            @RequestParam(required = false , defaultValue = "복순이") String name
    )
    {
        System.out.println("id : " + id1 );
        System.out.println("name : " + name );

        return id1 + " " + name;
    }

    // 사용자 정보를 등록하는 api
    @TimeChecker
    @PostMapping("/user")
    public User registUser(User user) {

        System.out.println("id : " + user.getId() );
        System.out.println("name : " + user.getName() );

        return user;

    }

    @PutMapping("/user")
    public User updateUser(User user) {

        user.setId("abc123");
        user.setName("홍길동");

        System.out.println("id : " + user.getId());
        System.out.println("name : " + user.getName());

        return user;
    }
}
