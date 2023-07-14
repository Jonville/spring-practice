package com.tj.edu.practice6.model;

import com.tj.edu.practice6.model.enums.Nation;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
@Table(name = "member")
@EntityListeners(value = {MemberEntityListener.class})
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NonNull
    @Column(nullable = false)
    private String name;
    private String email;

    @Column(name = "colTest2" , unique = true , updatable = false)
    private Integer test2;

//    @OneToMany(fetch = FetchType.EAGER) // Address class 에 있는걸 JOIN 하는거
//    private List<Address> address;

    @OneToMany
    private List<MemberLogHistory> memberLogHistories;


    @Transient  // 잠깐 Column 자체를 DB 에서 제외 시켜준다. ( JAVA 에서만 실행시키고 싶을때 사용 )
    private String testData;

    @Column(columnDefinition = "ENUM")      // 이렇게 해주면 좀더 안전함
    @Enumerated(value = EnumType.STRING)
    private Nation nation;

    @PreRemove
    public void preRemove1() {
        System.out.println(">>> preRemove1");
    }
    @PostPersist
    public void afterInsert1() {
        System.out.println(">>> afterInsert1");
    }
    @PostLoad
    public void afterLoad1() {
        System.out.println(">>> afterLoad1");
    }
    @PostUpdate
    public void afterUpdate1() {
        System.out.println(">>> afterUpdate1");
    }
    @PostRemove
    public void afterRemove1() {
        System.out.println(">>> afterRemove1");
    }


}
