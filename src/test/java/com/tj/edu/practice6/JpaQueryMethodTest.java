package com.tj.edu.practice6;

import com.tj.edu.practice6.model.*;
import com.tj.edu.practice6.model.enums.Nation;
import com.tj.edu.practice6.repository.*;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
class JpaQueryMethodTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MemberLogHistoryRepository memberLogHistoryRepository;

//    @Test
//    void jpaQueryMethodTest1() {
//        // SELECT문 : email값으로 특정 row데이터 가져오기
//        Set<Member> memberLeesunsin = memberRepository.readByEmail("leesunsin@gmail.com");
//        memberLeesunsin.forEach(System.out::println);
//
//        // SELECT문 : name값으로 특정 row데이터들 가져오기
//        List<Member> memberHongildongList = memberRepository.findByName("홍길동");
//        memberHongildongList.forEach(System.out::println);
//
//        Optional<Member> memberOpt1 = memberRepository.findByCreateAt(LocalDateTime.MAX);
//        System.out.println(memberOpt1);
//        System.out.println("findByName : " + memberRepository.findByName("이순신"));
//
//        // "이순신" 친구가 있는지 확인 (boolean 형)
//        System.out.println("existsby : " + memberRepository.existsByName("이순신"));
//
//        // SELECT문 : count 로 뽑아보자
//        System.out.println("countBy : " + memberRepository.countByName("홍길동"));
//
//        // topNumber, firstNumber , lastNumber
//        memberRepository.findFirst1ByEmail("namsun@thejoeun.com").forEach(System.out::println);
//        memberRepository.findTop1ByEmail("namsun@thejoeun.com").forEach(System.out::println);
//        memberRepository.findTop2ByEmail("namsun@thejoeun.com").forEach(System.out::println);
//
//        // distinct
//        memberRepository.findDistinctByEmail("namsun@thejoeun.com").forEach(System.out::println);
//        memberRepository.findDistinctByEmail("namsun@thejoeun.com").forEach(s -> System.out.println(s));    // 위에거랑 같은 의미 (람다형식)
//
//    }
//
//    @Test
//    void jpaQueryMethodTest2() {
//        List<Member> memberList = memberRepository.findByNameAndEmail("홍길동", "mars@thejoeun.com");
//        memberList.forEach(s -> System.out.println(s));
//
//        List<Member> memberList2 = memberRepository.findByNameOrEmail("박남순", "mars@thejoeun.com");
//        memberList2.forEach(s -> System.out.println(s));
//
//        List<Member> memberList3 = memberRepository
//                .findByNameOrEmailOrCreateAt(
//                        "이순신",
//                        "mars@thejoeun.com",
//                        LocalDateTime.MAX);
//        memberList3.forEach(System.out::println);
//
//        List<Member> memberList4 = memberRepository.findByCreateAtAfter(LocalDateTime.now().minusDays(1L));
//        memberList4.forEach(s -> System.out.println(s));
//
//        List<Member> memberList5 = memberRepository.findByCreateAtGreaterThanEqual(LocalDateTime.now().minusDays(1L));
//        memberList5.forEach(s -> System.out.println(s));
//
//        List<Member> memberList6 = memberRepository.findByIdGreaterThanEqual(3L);
//        memberList6.forEach(s -> System.out.println(s));
//
//        List<Member> memberList7 = memberRepository
//                .findByCreateAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L));
//        memberList7.forEach(s -> System.out.println(s));
//
//        List<Member> memberList8 = memberRepository.findByIdGreaterThanEqualAndIdLessThanEqual(3L, 6L);
//        memberList8.forEach(System.out::println);
//
//        List<Member> memberList10 = memberRepository.findByEmailLike("%joeun%");
//        memberList10.forEach(System.out::println);
//
//        List<Member> memberList11 = memberRepository
//                .findByNameIn(Lists.newArrayList("홍길동" , "강감찬"));
//        memberList11.forEach(System.out::println);
//
//        List<Member> memberList12 = memberRepository
//                .findByNameContains("강");
//        memberList12.forEach(System.out::println);
//
//        List<Member> memberList13 = memberRepository
//                .findByNameStartingWith("강");
//        memberList13.forEach(System.out::println);
//
//        List<Member> memberList14 = memberRepository
//                .findByNameEndingWith("감");
//        memberList14.forEach(System.out::println);
//
//        List<Member> memberList15 = memberRepository
//                .findByNameOrderByIdDesc("홍길동");
//        memberList15.forEach(System.out::println);
//
//        List<Member> memberList16 = memberRepository
//                .findByAddressIsEmpty();
//        memberList16.forEach(System.out::println);
//
//        Page<Member> pageMember = memberRepository
//                .findByName("홍길동",
//                        PageRequest.of(0, 3 ,
//                                Sort.by(Sort.Order.desc("id"))));
//
//        List<Member> memberList17 = pageMember.getContent();
//        memberList17.forEach(System.out::println);
//
//    }

    @Test
    void jpaSchemaTest() throws InterruptedException {
        Member member = Member.builder()
                .name("이미라")
                .email("imila@naver.com")
//                .createAt(LocalDateTime.now())
//                .updateAt(LocalDateTime.now())
                .build();

        member = memberRepository.saveAndFlush(member); // insert

        Thread.sleep(1000);

        member.setName("gjgjgj");
        member.setUpdateAt(LocalDateTime.now());
        memberRepository.saveAndFlush(member);           // update

        member.setTest2(56789);
        memberRepository.saveAndFlush(member);           // update

    }

    @Test
    void boardColumnTest() {
        Board board1 = Board.builder()
                .boardNo("1")
                .title("ㅇㅇㅇ")
                .build();

        boardRepository.save(board1);
    }

    @Test
    void jpaEnumTest() {
        Member member = Member.builder()
                .name("이미라")
                .email("imira@naver.com")
//                .createAt(LocalDateTime.now())
//                .updateAt(LocalDateTime.now())
                .nation(Nation.CHINA)
                .build();

        memberRepository.save(member);
    }

    @Test
    void jpaEventListener() {
        Member member = Member.builder()
                .name("홍숭대")
                .email("hongsungdae@naver.com")
//                .createAt(LocalDateTime.now())
//                .updateAt(LocalDateTime.now())
                .build();

        memberRepository.save(member);      // insert (PrePersist , PostPersist)

        System.out.println(">>>>>>>>>>>!!!!" + member);

        Member member2 = memberRepository.findById(1L).orElseThrow(RuntimeException::new);  // select (PostLoad)
        member2.setName("박근혜");

        memberRepository.save(member2);     // update (PreUpdate , PreRemove)

        memberRepository.deleteById(3L);    // delete (PreRemove , PostRemove)

        member2.setName("이은표");
        memberRepository.save(member2);
    }

    @Test
    void bookRepositoryTest() throws InterruptedException {
        Book book = Book.builder()
                .name("표준orm JPA 프로그래밍")
                .author("김한선")
                .build();

        Book book2 = bookRepository.save(book);

        Thread.sleep(1000);

        book2.setAuthor("박봉남");
        bookRepository.save(book2);
    }

    @Test
    void addressRepositoryTest() throws InterruptedException {
        Address address = Address.builder()
                .zipcode("아파트 1530 동 1569 호")
                .build();

        Address address2 = addressRepository.save(address);

    }

    @Test
    void getOneToManyTest() {
        Member member = Member.builder()
                .name("홍숭대")
                .email("hongsungdae@naver.com")
                .build();
        memberRepository.save(member);

        member.setName("박근혜");
        memberRepository.save(member);

//        List<MemberLogHistory> memberLogHistories = memberLogHistoryRepository.findByMemberId(member.getId());
//        Optional<Member> optMember2 = memberRepository.findById(memberLogHistories.get(0).getMemberId());

        memberRepository.findAll();
    }

}