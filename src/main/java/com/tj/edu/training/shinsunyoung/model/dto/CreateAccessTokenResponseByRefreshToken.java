package com.tj.edu.training.shinsunyoung.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateAccessTokenResponseByRefreshToken {
    private String accessToken;
}
