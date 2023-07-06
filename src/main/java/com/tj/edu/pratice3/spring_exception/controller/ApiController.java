package com.tj.edu.pratice3.spring_exception.controller;

import com.tj.edu.pratice3.spring_exception.dto.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
public class ApiController {

    // 사용자 정보를 조회하는 api
    @GetMapping("/user")
    public User user(@Size(min = 2) @RequestParam String name ,
                     @RequestParam int age
    ) {

        User user = null;
        user.setName(name);
        user.setAge(age);

        double a = 1/0;

        return user;
    }

    // 사용자 정보를 등록하는 api
    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody User user) {
        System.out.println(user);

        return ResponseEntity.ok(user);
    }
}
