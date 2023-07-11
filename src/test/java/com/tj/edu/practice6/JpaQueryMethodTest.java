package com.tj.edu.practice6;

import com.tj.edu.practice6.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaQueryMethodTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void jpaQueryMethodTest1() {

    }

}