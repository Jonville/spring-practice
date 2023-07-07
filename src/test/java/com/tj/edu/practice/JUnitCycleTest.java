package com.tj.edu.practice;

import org.junit.jupiter.api.*;

public class JUnitCycleTest {

    @BeforeAll
    static void beforeAll(){ System.out.println("@BeforeAll이 실행됨!"); }

    @BeforeEach
    void beforeEach(){ System.out.println("@BeforeEach 실행됨!"); }

    @Test
    void test1() { System.out.println("test1이 실행됨"); }

    @Test
    void test2() { System.out.println("test2이 실행됨"); }

    @Test
    void test3() { System.out.println("test3이 실행됨"); }

    @AfterAll
    static void afterAll(){ System.out.println("@afterAll이 실행됨!");}

    @AfterEach
    void afterEach(){ System.out.println("@afterEach 실행됨!"); }
}
