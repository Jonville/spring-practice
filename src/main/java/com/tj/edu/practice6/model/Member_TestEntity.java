package com.tj.edu.practice6.model;

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
public class Member_TestEntity {

    @Id
    private Long uid;
    private String uname;
    private String upassword;
    private LocalDateTime cdate;
    private LocalDateTime udate;

}
