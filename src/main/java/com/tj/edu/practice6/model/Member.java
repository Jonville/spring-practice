package com.tj.edu.practice6.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "member" ,
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})},
        indexes = {@Index(columnList = "name") , @Index(columnList = "email")})
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

    @OneToMany(fetch = FetchType.EAGER) // Address class 에 있는걸 JOIN 하는거
    private List<Address> address;
}
