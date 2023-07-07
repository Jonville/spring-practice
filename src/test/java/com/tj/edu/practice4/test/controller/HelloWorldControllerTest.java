package com.tj.edu.practice4.test.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("helloWorld-GetMapping HTTP Method 테스트")
    @Test
    public void helloWorldSuccess() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/helloworld"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello-world"));
    }

    @DisplayName("helloWorld-GetMapping HTTP Method 오류 테스트")
    @Test
    public void helloWorldFailed() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/helloworld1"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(""));
    }

}