package jpamarket.jpashopV2.repository;

import jpamarket.jpashopV2.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    // 멤버 모두 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 이름으로 멤버 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
