package com.tj.edu.practice4.aop.aops;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAop {

    @Pointcut("execution(* com.tj.edu.practice4.aop.controller..*.*(..))")
    private void logPointCut1() {}

    @Before("logPointCut1()")
    public void beforeLog(JoinPoint joinPoint) {
        System.out.println("--------------------");
        System.out.println("putMapping Aop 실행!");
        System.out.println("--------------------");
    }

    @After("logPointCut1()")
    public void AfterLog(JoinPoint joinPoint) {
        System.out.println("--------------------");
        System.out.println("putMapping Aop 실행 (After)!");
        System.out.println("--------------------");
    }

}
