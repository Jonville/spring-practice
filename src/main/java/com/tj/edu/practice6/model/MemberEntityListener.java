package com.tj.edu.practice6.model;

import com.tj.edu.practice6.repository.MemberLogHistoryRepository;
import com.tj.edu.practice6.util.SpringBeanUtils;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;

public class MemberEntityListener{

    @PostUpdate
    @PostPersist
    public void postPersistAndPostUpdate(Object o) {    // 한꺼번에 가능
        MemberLogHistoryRepository memberLogHistoryRepository = SpringBeanUtils.getBean(MemberLogHistoryRepository.class);

        Member member = (Member) o;

        MemberLogHistory memberLogHistory = MemberLogHistory.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();

        memberLogHistoryRepository.save(memberLogHistory);
    }
   
}
