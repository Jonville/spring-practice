package com.tj.edu.practice6.repository;

import com.tj.edu.practice6.model.Member;
import com.tj.edu.practice6.model.MemberLogHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberLogHistoryRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberLogHistoryRepository memberLogHistoryRepository;

    @Test
    void selectRelationTest1(){
        givenMember();
        givenMember2();

        List<MemberLogHistory> memberLogHistoryList = memberLogHistoryRepository.findAll();

        memberLogHistoryList.forEach(System.out::println);

        memberLogHistoryList.get(0).getMember();


    }
    @Test
    void selectRelationTest2(){
        givenMember();
        givenMember2();

//     memberLogHistoryRepository.findByEmail("hongsungdae@naver.com").getMember();
    }

    private Member givenMember() {
        Member member = Member.builder()
                .name("홍숭대")
                .email("hongsungdae@naver.com")
                .build();
        Member member2 = memberRepository.save(member);
        member2.setName("홍숭대22");

        return memberRepository.save(member2);

    }
    private Member givenMember2() {
        Member member = Member.builder()
                .name("이동휘")
                .email("leedonghui@naver.com")
                .build();
        return memberRepository.save(member);
    }
}