package com.tj.edu.training.shinsunyoung.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;

    /**
     * 웹 보안 설정 control
     * (1) 스프링 시큐리티 비활성화
     *
     * @return
     */
    @Bean   // 인증을 받지않고 통과 시킬수 있게 함
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()      // 말그대로 ignore 무시하고 들어간다는 의미
//                .requestMatchers(toH2Console))
                .requestMatchers("/static/**") // static 파일을 접근할수있음
                .requestMatchers("/images/**"); // static -> images 안에 png 파일을 접근할수있음
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .requestMatchers("/login", "/signup", "/user").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/articles")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                )
//                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}