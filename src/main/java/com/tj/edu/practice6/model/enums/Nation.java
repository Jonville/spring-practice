package com.tj.edu.practice6.model.enums;

import lombok.Getter;

@Getter
public enum Nation {
    KOREA(10),
    JAPAN(30),
    CHINA(3),
    ENGLAND(6),
    기타(23);

    private final int value;

    Nation(int value){
        this.value=value;
    }
}
