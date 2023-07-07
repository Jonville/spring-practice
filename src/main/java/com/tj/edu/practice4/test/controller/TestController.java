package com.tj.edu.practice4.test.controller;

import com.tj.edu.practice4.test.model.Member;
import com.tj.edu.practice4.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test1")
    public List<String> getAllStringValue() {
        return testService.getAllStringValue();
    }

    @GetMapping("/test2")
    public List<Member> getAllMembers() {
        return testService.getAllMembers();
    }


}
