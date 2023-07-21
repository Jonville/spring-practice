package com.tj.edu.training.shinsunyoung.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccessTokenRequestByRefreshToken {
    private String refreshToken;
}
