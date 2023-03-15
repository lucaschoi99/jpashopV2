package jpamarket.jpashopV2.service;

import jpamarket.jpashopV2.domain.Member;
import jpamarket.jpashopV2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    public Long join(Member member) {
        checkDuplicateMember(member);
        Member saved = memberRepository.save(member);
        return saved.getId();
    }

    private void checkDuplicateMember(Member member) {
        List<Member> dupMembers = memberRepository.findByName(member.getName());
        if (!dupMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 전체회원 조회
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public Member findMember(Long id) {
        return memberRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }



}
