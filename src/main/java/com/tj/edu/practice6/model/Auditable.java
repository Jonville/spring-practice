package com.tj.edu.practice6.model;

import java.time.LocalDateTime;

public interface Auditable {
    LocalDateTime getCreateAt();    // Getter

    LocalDateTime getUpdateAt();

    void setCreateAt(LocalDateTime createAt);   // Setter

    void setUpdateAt(LocalDateTime updateAt);
}
