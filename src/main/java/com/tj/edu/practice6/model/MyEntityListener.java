package com.tj.edu.practice6.model;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class MyEntityListener { // 공통화 된 eventListener

    @PrePersist
    public void prePersist(Object o) {      // 이렇게 만들어주면 일일히 insert문에 하나하나 안넣어줘도됨
        if(o instanceof Auditable) {
            ((Auditable) o).setCreateAt(LocalDateTime.now());
//            ((Auditable) o).setUpdateAt(LocalDateTime.now());
        }
    }
    @PreUpdate
    public void preUpdate(Object o) {
        ((Auditable) o).setCreateAt(LocalDateTime.now());
    }
}
