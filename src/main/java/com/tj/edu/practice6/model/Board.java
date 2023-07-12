package com.tj.edu.practice6.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {
    @Id
    @Column(columnDefinition = "varchar(100)  comment 'primary key'")
    private String boardNo;
    private String boardKind;
    private String keywordType;
    @Column(columnDefinition = "varchar(100) DEFAULT NULL comment '유저아이디'")
    private String userId;
    @Column(updatable = false)
    private LocalDateTime cdatetime;
    @Column(insertable = false)
    private LocalDateTime udatetime;
    private String title;
    private String content;
    private String viewCnt;
    private String delYn;
    private String replyYn;
    private String imgNo;
    private String replyNo;
    private String imgSrc;
    private String imgName;
    private String orgName;
    private String imgType;
}
