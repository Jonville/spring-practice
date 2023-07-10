package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.AdminUser;
import com.tj.edu.practice5.jpa.model.Member;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AdminUserRepository adminUserRepository;

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

//        // delete 문
//        System.out.println("delete 문--------------------------------");
////        memberRepository.deleteAll();
//        memberRepository.deleteAllInBatch();
//        List<Member> memberList4 = memberRepository.findAll();
//        memberList4.forEach(System.out::println);
    }


    @Test
    void crud2() {
        // insert문 (name과 create_at 컬림이 null이 아닌 insert)
        Member member = Member.builder()
                .name("이명박")
                .createAt(LocalDateTime.now())
                .build();

        memberRepository.save(member);

        // insert문 (update_at 컬럼이 null이 아닌 insert)
        Member member2 = Member.builder()
                .updateAt(LocalDateTime.now())
                .build();
        memberRepository.save(member2);

        // insert문 (id : 15, name : 박조은 , email : parkjoeun@gmail.com , create_at : 현재시간)
        Member member3 = Member.builder()
                .id(15L)        // 이거 실제로 DB에 적용 안됨!
                .name("박조은")
                .email("parkjoeun@gmail.com")
                .createAt(LocalDateTime.now())
                .build();
        memberRepository.save(member3);

        // select문
        Optional<Member> memberOptional =  memberRepository.findById(3L);
        memberOptional.orElseThrow(RuntimeException::new);
        System.out.println(memberOptional);

        // id : 7 , 3을 가진 행값을 가져오는 java jpa 코드 작성 (findAll 로) select 문
        List<Member> listMember = memberRepository.findAllById(Lists.newArrayList(4L , 8L));
        listMember.forEach(System.out::println);

        // select count 함수 만들기
        System.out.println(" 회원 수는 : " + memberRepository.count() + " 입니다");

        // select exist 함수 -> 있는지 없는지 확인시켜주는 친구
        boolean isFiveNumberMember = memberRepository.existsById(5L);
        if(isFiveNumberMember) {
            System.out.println("5번 회원 존재");
        }
        boolean isNinetyNumberMember = memberRepository.existsById(90L);
        if(isNinetyNumberMember) {
            System.out.println("90번 회원 존재");
        }
        
        // select page 함수
        Page<Member> membersPage = memberRepository.findAll(PageRequest.of(1, 4));
        System.out.println("page : " + membersPage);
        System.out.println("TotalElements : " + membersPage.getTotalElements());
        System.out.println("TotalPage : " + membersPage.getTotalPages());
        System.out.println("NumberOfElements : " + membersPage.getNumberOfElements());
        System.out.println("Sort : " + membersPage.getSort());
        System.out.println("Size : " + membersPage.getSize());

        List<Member> memberList2 = membersPage.getContent();
        memberList2.forEach(System.out::println);

        // jpa find example이용 ( select )
        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("name")    // name 을 무시하고 찾기
//                .withMatcher("email", startsWith())   //~~ 로 끝나는 email 로 찾기
                ;

        Example<Member> memberExample = Example.of(Member.builder()
                .name("홍길동")
//                .email("thejoeun.com")
                .build() , matcher);

        memberRepository.findAll(memberExample).forEach(System.out::println);

    }

    @DisplayName("semmiProject sqlmapper 관련 xml sql 코드를 jpa 자바코드로 변환 테스트")
    @Test
    void crudSemiSqlMapper() {

        AdminUser adminUser = AdminUser.builder()
                .name("홍길동")
                .nick("Honghong")
                .email("hong@gmail.com")
                .phone("010-1234-5678")
                .build();

        adminUserRepository.save(adminUser);

        System.out.println(adminUser);

    }
    
    
}