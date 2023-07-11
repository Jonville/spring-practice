package com.tj.edu.practice6.repository;

import com.tj.edu.practice6.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member , Long> {

}
