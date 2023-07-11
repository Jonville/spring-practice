package com.tj.edu.practice5.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NonNull
    private String name;
    private String email;
//    @NonNull
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
