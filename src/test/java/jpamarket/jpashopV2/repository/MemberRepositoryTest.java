package jpamarket.jpashopV2.repository;

import jpamarket.jpashopV2.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

}