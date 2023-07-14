package com.tj.edu.practice6.repository;

import com.tj.edu.practice6.model.Member;
import com.tj.edu.practice6.model.MemberLogHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberLogHistoryRepository extends JpaRepository<MemberLogHistory , Long> {
    List<MemberLogHistory> findByMemberId(Long memberId);
}
