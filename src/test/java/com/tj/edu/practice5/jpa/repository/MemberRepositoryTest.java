package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.Member;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void crud() {
//        Member member1 = new Member(1L , "홍길동" , null , LocalDateTime.now() ,null);
//        // create 문
//        Member copyMember = memberRepository.save(member1);    // SQL -> INSERT INTO User VALUES (~~~~)
//        System.out.println("member -> " + copyMember);

        // select all문
        System.out.println("select all 문--------------------------------");
        // == select * from member
        List<Member> memberList = memberRepository.findAll(Sort.by(Sort.Direction.DESC , "name"));

        // jdk 1.8에서 사용된 stream 기술
        memberList.forEach(System.out::println);

//        for(Member member : memberList) {
//            System.out.println(member.toString());
//        }

        // select where 문
        System.out.println("select where 문--------------------------------");
        // == select * from member where id in (1 , 3)
        List<Member> memberList2 = memberRepository.findAllById(Lists.newArrayList(1L , 3L));
        memberList2.forEach(System.out::println);

        // update 문
        // 1번 가진 id가 있다면 update, 없으면 create문 발생!
        System.out.println("update 문--------------------------------");
        Member member1 = new Member(1L , "홍길동" , "이메일주소오" , LocalDateTime.now() ,null);
        memberRepository.save(member1);
        List<Member> memberList3 = memberRepository.findAll();
        memberList3.forEach(System.out::println);

        // delete 문
        System.out.println("delete 문--------------------------------");
//        memberRepository.deleteAll();
        memberRepository.deleteAllInBatch();
        List<Member> memberList4 = memberRepository.findAll();
        memberList4.forEach(System.out::println);
    }


}