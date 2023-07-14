package com.tj.edu.practice6.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface TimeAuditable  {
    LocalDateTime getCreateAt();
    LocalDateTime getUpdateAt();

    void setCreateAt(LocalDateTime createAt);
    void setUpdateAt(LocalDateTime updateAt);
}
