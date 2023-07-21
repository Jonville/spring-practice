package com.tj.edu.training.shinsunyoung.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tj.edu.training.shinsunyoung.config.jwt.JwtFactory;
import com.tj.edu.training.shinsunyoung.config.jwt.JwtProperties;
import com.tj.edu.training.shinsunyoung.model.RefreshToken;
import com.tj.edu.training.shinsunyoung.model.User;
import com.tj.edu.training.shinsunyoung.model.dto.CreateAccessTokenRequestByRefreshToken;
import com.tj.edu.training.shinsunyoung.repository.RefreshTokenRepository;
import com.tj.edu.training.shinsunyoung.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TokenApiControllerTest {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void mockMvcSetUp() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();

    }

    @DisplayName("createNewAccessToken: 새로운 액세스 토큰을 발급한다.")
    @Test
    public void createNewAccessToken() throws Exception {
        // given
        final String url = "/api/token";

        User testUser = userRepository.save(User.builder()
                .email("user@gmail.com")
                .password("abcd")
                .build());

        String refreshToken = JwtFactory.builder()
                .claims(Map.of("id", testUser.getId()))
                .build()
                .createToken(jwtProperties);

        refreshTokenRepository.save(new RefreshToken(testUser.getId(), refreshToken));

        CreateAccessTokenRequestByRefreshToken request = new CreateAccessTokenRequestByRefreshToken();
        request.setRefreshToken(refreshToken);
        final String requestBody = objectMapper.writeValueAsString(request);

        // when
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
    }

}