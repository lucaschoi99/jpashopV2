package jpamarket.jpashopV2.repository;

import jpamarket.jpashopV2.domain.Address;
import jpamarket.jpashopV2.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void memberTest() {
        Member member = new Member();
        member.setName("bin");

        Long id = memberRepository.save(member);
        Member foundMember = memberRepository.findMember(id);

        assertThat(member.getId()).isEqualTo(foundMember.getId());
        assertThat(member.getName()).isEqualTo(foundMember.getName());
        assertThat(member).isEqualTo(foundMember);

    }

    @Test
    @Transactional
    public void findMember() {
        Member member1 = new Member("bin1", new Address("seoul"));
        Member member2 = new Member("bin2", new Address("busan"));

        Long id_1 = memberRepository.save(member1);
        Long id_2 = memberRepository.save(member2);
        Member findMember1 = memberRepository.findMember(id_1);
        List<Member> findAll = memberRepository.findAll();

        assertThat(findMember1.getId()).isEqualTo(member1.getId());
        assertThat(findAll.size()).isEqualTo(2);

    }

}