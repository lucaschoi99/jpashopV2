package jpamarket.jpashopV2.repository;

import jpamarket.jpashopV2.domain.Address;
import jpamarket.jpashopV2.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void memberTest() {
        Member member = new Member();
        member.setName("bin");

        Long id = memberRepository.save(member).getId();
        Member foundMember = memberRepository.findById(id).orElseThrow(IllegalAccessError::new);

        assertThat(member.getId()).isEqualTo(foundMember.getId());
        assertThat(member.getName()).isEqualTo(foundMember.getName());
        assertThat(member).isEqualTo(foundMember);

    }

    @Test
    @Transactional
    public void findMember() {
        Member member1 = new Member("bin1", new Address("seoul"));
        Member member2 = new Member("bin2", new Address("busan"));

        Long id_1 = memberRepository.save(member1).getId();
        Long id_2 = memberRepository.save(member2).getId();
        Member findMember1 = memberRepository.findById(id_1).orElseThrow(IllegalAccessError::new);
        List<Member> findAll = memberRepository.findAll();

        assertThat(findMember1.getId()).isEqualTo(member1.getId());
        assertThat(findAll.size()).isEqualTo(2);

    }

}