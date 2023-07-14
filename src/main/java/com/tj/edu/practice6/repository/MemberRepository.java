package com.tj.edu.practice6.repository;

import com.tj.edu.practice6.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MemberRepository extends JpaRepository<Member , Long> {
//    Set<Member> readByEmail(String email);   // 매개변수 email 을 받아 email 을 찾아라
//    List<Member> findByName(String name);   // 동명이인 친구들 리스트로 쫙 뽑아라
//    Optional<Member> findByCreateAt(LocalDateTime localDateTime);   //
//
//    boolean existsByName(String name);  // "이름" 존재하는지 확인
//    Integer countByName(String name);   // "이름" 을 가진 사람들 카운트
//
//    List<Member> findTop1ByEmail(String email);     // limit 1
//    List<Member> findFirst1ByEmail(String email);   // limit 1
//    List<Member> findTop2ByEmail(String email);     // limit 2
//
//    List<Member> findDistinctByEmail(String email);
//
//    // -------- 컬럼 여러개로 이용한 쿼리문 --------
//
//    List<Member> findByNameAndEmail(String name, String email); // 이름과 이메일을 가져온다
//
//    List<Member> findByNameOrEmail(String name , String email);
//
//    // And , Or 몇개든 써든 상관없음
//    List<Member> findByNameOrEmailOrCreateAt(String name, String email, LocalDateTime localDateTime);
//
//    List<Member> findByCreateAtAfter(LocalDateTime yesterday);  // create_at 이후에 있는 친구들 찾기
//
//    List<Member> findByCreateAtGreaterThanEqual(LocalDateTime yesterday);  // create_at 이후에 있는 친구들 찾기
//
//    List<Member> findByIdGreaterThanEqual(Long id);
//
//    List<Member> findByCreateAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);
//
//    List<Member> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);
//
//    List<Member> findByEmailIsNotNull();
//
//    List<Member> findByEmailLike(String email);
//
//    List<Member> findByNameIn(List<String> nameList);   // 리스트로 받아야함
//
//    List<Member> findByNameContains(String name);   // "%단어%"
//
//    List<Member> findByNameStartingWith(String name);   // "단어%"
//
//    List<Member> findByNameEndingWith(String name);     // "%단어"
//
//    List<Member> findByNameOrderByIdDesc(String name);    // SQL문 order by Desc id 순으로
//
//    List<Member> findByAddressIsEmpty();
//
//    Page<Member> findByName(String name , Pageable pageable);

    Member findByEmail (String email);
}
