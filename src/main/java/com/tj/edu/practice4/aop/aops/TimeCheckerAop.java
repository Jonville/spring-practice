package com.tj.edu.practice4.aop.aops;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimeCheckerAop {

    // com.tj.edu.practice4.aop.controller 패키지에 있는 모든 컨트롤러에 진입했을때 실행하라는 설정
    @Pointcut("execution(* com.tj.edu.practice4.aop.controller..*.*(..))")
    private void cut1() {}

    @Pointcut("@annotation(com.tj.edu.practice4.aop.annotations.TimeChecker)")
    private void timeChecker1() {}

    // com.tj.edu.practice4.aop.controller에서 실행되기 전에 실행되는 메소드
    @Before("cut1() && timeChecker1()")
    public void before1(JoinPoint joinPoint){
        System.out.println("TimeCheckerAop의 before실행됨");
    }

    @Around("cut1() && timeChecker1()")
    public Object around1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // pointcut 실행된 함수를 실행해줌
        Object result =  proceedingJoinPoint.proceed();

        stopWatch.stop();
        System.out.println("값 : " + result);
        System.out.println("실행시간 : " + stopWatch.getTotalTimeSeconds() + "초");

        return result;
    }
}
