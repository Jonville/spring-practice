package com.tj.edu.practice6.model;

import com.tj.edu.practice6.repository.MemberLogHistoryRepository;
import com.tj.edu.practice6.util.SpringBeanUtils;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberEntityListener {

//    @Autowired        // 이렇게 사용할수가 없음
//    MemberLogHistoryRepository memberLogHistoryRepository;

    @PostPersist
    @PostUpdate
    public void afterMemberSave(Object o)  {
        // memberLogHistoryRepository 을 가져올땐 이렇게 해준다
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
