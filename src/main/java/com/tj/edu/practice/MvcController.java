package com.tj.edu.practice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {
    @GetMapping(value ="/test2")
    public String test2() {
        return "test2";
    }
}
