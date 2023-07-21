package com.tj.edu.training.shinsunyoung.controller;

import com.tj.edu.training.shinsunyoung.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

    // 로그인 페이지로 이동
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/oauth2login")
    public String oauth2login() {
        return "oauth2login";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    // 회원가입 페이지로 이동
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}
