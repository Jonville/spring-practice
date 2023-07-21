package com.tj.edu.training.shinsunyoung.controller;

import com.tj.edu.training.shinsunyoung.model.dto.CreateAccessTokenRequestByRefreshToken;
import com.tj.edu.training.shinsunyoung.model.dto.CreateAccessTokenResponseByRefreshToken;
import com.tj.edu.training.shinsunyoung.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponseByRefreshToken> createNewAccessTokenByRefreshToken(
            @RequestBody CreateAccessTokenRequestByRefreshToken createAccessTokenRequestByRefreshToken
    ) {
        // refresh토큰으로 access토큰 발급
        String newAccessToken = tokenService.createNewAccessToken(createAccessTokenRequestByRefreshToken.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponseByRefreshToken(newAccessToken));
    }
}
