package com.tj.edu.practice4.interceptor_filter.interceptors;

import com.tj.edu.practice4.interceptor_filter.annotations.AuthLog;
import com.tj.edu.practice4.interceptor_filter.annotations.AuthUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.annotation.Annotation;

@Component
@SuppressWarnings("unchecked")
public class AuthInterceptor implements HandlerInterceptor {

    @Override   // 암호화 할때 주로 사용
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerInterceptor.super.preHandle(request, response, handler);

        System.out.println("AuthInterceptor preHandle 실행");

        // request URL 문자열을 parsing해서 특정한 url만 interceptor 후 처리가 가능
        // String requestURI = request.getRequestURI();    // ex) /authapi/~~ , /api/~~~

        boolean isValidAccessUserAnnotation = checkAccessValidAnnotation(handler , AuthUser.class);
        boolean isValidAccessLogAnnotation = checkAccessValidAnnotation(handler , AuthLog.class);

        // AuthUser 혹은 AuthLog 어노테이션이 적용된 클래스(Controller)는 통과
        if(isValidAccessUserAnnotation || isValidAccessLogAnnotation){
            return true;
        }

        return false;
    }

    private boolean checkAccessValidAnnotation(Object handler , Class clazz) {

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Object authUserHandlerMethod = handlerMethod.getMethodAnnotation(clazz);
        Annotation annotations =  handlerMethod.getBeanType().getAnnotation(clazz);

        if(authUserHandlerMethod != null || annotations != null) {
            System.out.println("----------------------------------------------");
            System.out.println("어노테이션 체크 class이름 : " + clazz.getName());

            return true;
        }

        return false;

    }

    @Override   // 전화번호 , 아이디 등 복호화 할때 주로 사용
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        
        System.out.println("AuthInterceptor postHandle 실행");


    }

    @Override   // 암, 복 호화 된것 만 다른 db에 넘기기
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

        System.out.println("AuthInterceptor afterCompletion 실행");
        System.out.println("----------------------------------------------");

    }
}
