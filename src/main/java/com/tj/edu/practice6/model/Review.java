package com.tj.edu.practice6.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "review")
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(500)" , nullable = false)
    private String title;
    @Column(columnDefinition = "text")
    private String content;

    @ManyToOne
    @ToString.Exclude
    private Book book;

    @ManyToOne
    @ToString.Exclude
    private Member member;

}
