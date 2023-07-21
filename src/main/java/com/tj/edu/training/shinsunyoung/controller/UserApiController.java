package com.tj.edu.training.shinsunyoung.controller;

import com.tj.edu.training.shinsunyoung.model.User;
import com.tj.edu.training.shinsunyoung.model.dto.AddUserRequest;
import com.tj.edu.training.shinsunyoung.model.dto.LoginRequest;
import com.tj.edu.training.shinsunyoung.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    // 실제 회원가입 등록
    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.register(request);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(LoginRequest request) {
//        System.out.println("로그인 서비스 동작");
        User user = userService.login(request);

        return "redirect:/user?" + user.getAccessToken();
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    @GetMapping("/oauth2logout")
    public String oauth2logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/oauth2login";
    }
}
