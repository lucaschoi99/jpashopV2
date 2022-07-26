package jpamarket.jpashopV2.repository;

import jpamarket.jpashopV2.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // 멤버 저장
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    // 멤버 단건 조회
    public Member findMember(Long id) {
        return em.find(Member.class, id);
    }

}
