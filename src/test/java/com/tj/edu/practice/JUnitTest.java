package com.tj.edu.practice;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnitTest {

    @DisplayName("기본테스트1")
    @Test
    public void junitTest() {
        int a = 1;
        int b = 2;

        int sum = 3;

//        Assertions.assertEquals(a+b , sum);

        String aStr = "test1";
        assertThat(a+b).isEqualTo(sum);
//        assertThat(a+b).isNotEqualTo(sum);
        assertThat(aStr).contains("aabc");
    }
    @DisplayName("기본테스트2")
    @Test
    public void junitTest2() {
        int a = 1;
        int b = 2;

        int sum = 3;

        Assertions.assertEquals(a+b , sum);

    }
}
