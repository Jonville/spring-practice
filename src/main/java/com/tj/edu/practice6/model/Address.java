package com.tj.edu.practice6.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(value = MyEntityListener.class)
public class Address implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String zipcode;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
