package com.tj.edu.practice5.lombok;

import com.tj.edu.practice5.lombok.model.Member;
import com.tj.edu.practice5.lombok.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class LombokTestApplicationTest {

    @BeforeEach
    void setUp() {
        System.out.println("-------------------------");
    }

    @DisplayName("Lombok 테스트")
    @Test
    void lombokTest() {

        User user = new User();
        user.setName("홍길동");
        user.setEmail("agildong@gmail.com");

        User user2 = new User("김복순" , "abc123@gmail.com" , null , null);
        User user3 = new User("짱구", "zzang9@gmail.com");

        System.out.println(user.getName());
        System.out.println(user.getEmail());

        System.out.println(user);
        System.out.println(user2);
        System.out.println(user3);

        // User Builder 를 이용한 user 객체 생성
        // null pointer exception을 방지하는 기능
        User user4 = User.builder()
                    .name("홍홍12")
                    .email("abd123@gmail.com")
                    .createAt(LocalDateTime.now())
                    .build();

        System.out.println(user4);

    }

    @DisplayName("Member 테스트")
    @Test
    void lomBokMemberTest() {
        Member member = new Member();
        member.setId(1L);

        Member member2 = Member.builder()
                    .age(30)
                    .id(9566559L)
                    .build();

        System.out.println(member.getId());
        System.out.println(member2);
    }

    @AfterEach
    void tearDown() {
        System.out.println("-------------------------");
    }

}