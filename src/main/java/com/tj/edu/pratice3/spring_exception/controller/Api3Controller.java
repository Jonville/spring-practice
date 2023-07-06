package com.tj.edu.pratice3.spring_exception.controller;

import com.tj.edu.pratice3.spring_exception.dto.User;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api3")
public class Api3Controller {

    @GetMapping("/user")
    public User user(@Size(min = 2) @RequestParam String name ,
                     @RequestParam(required = true) int age
    ) {

        double a = 1/0;

        User user = new User();
        user.setName(name);
        user.setAge(age);

        return user;
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity methodArgumentNotValidException(MissingServletRequestParameterException manve) {
        System.out.println("필수값을 보내주세요.");
        System.out.println(manve.getBody());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("age값을 넣어서 보내주세요.");
    }

}
