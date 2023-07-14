package com.tj.edu.practice6.repository;

import com.tj.edu.practice6.model.Address;
import com.tj.edu.practice6.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AddressRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AddressRepository addressRepository;


    @Test
    void addressOneToOneTest() {
        Member member = getGivenMember();
        Member member2 = getGivenMember();

        Address address = getGivenAddress(member);

        member2.setId(3L);
        getGivenAddress(member2);

        addressRepository.findById(1L);

    }

    private Member getGivenMember() {
        Member member = Member.builder()
                .name("홍홍길길동동")
                .email("honggilgilg@gmail.com")
                .build();

        return memberRepository.save(member);

    }

    private Address getGivenAddress(Member member) {

        Address address = Address.builder()
                .member(member)
                .zipcode("103동 2203호")
                .build();

        return addressRepository.save(address);
    }

}