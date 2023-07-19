package com.tj.edu.training.shinsunyoung.controller;

import com.tj.edu.training.shinsunyoung.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
