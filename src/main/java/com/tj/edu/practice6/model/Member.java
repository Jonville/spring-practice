package com.tj.edu.practice6.model;

import com.tj.edu.practice6.model.enums.Nation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "member" , uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NonNull
    @Column(nullable = false)
    private String name;
    private String email;

    @Column(name = "colTest2" , unique = true , updatable = false)
    private Integer test2;

//    @NonNull
//    @Column(updatable = false)  // createAt에 자주사용함. ( 생성될때 계속 업데이트되면 안되기때문 )
    private LocalDateTime createAt;
    @Column(columnDefinition = "datetime(6) DEFAULT now() comment '수정시간'",
            insertable = false , nullable = false) // 얘는 인서트할때는 적용시키면 안되기 떄문에 insertable = false 로 함
    private LocalDateTime updateAt;

    @OneToMany(fetch = FetchType.EAGER) // Address class 에 있는걸 JOIN 하는거
    private List<Address> address;

    @Transient  // 잠깐 Column 자체를 DB 에서 제외 시켜준다. ( JAVA 에서만 실행시키고 싶을때 사용 )
    private String testData;

    @Column(columnDefinition = "ENUM")      // 이렇게 해주면 좀더 안전함
    @Enumerated(value = EnumType.STRING)
    private Nation nation;
}
