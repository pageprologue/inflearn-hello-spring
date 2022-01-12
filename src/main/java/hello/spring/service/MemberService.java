package hello.spring.service;

import hello.spring.domain.Member;
import hello.spring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();

        } finally {
            long finish = System.currentTimeMillis();
            long time = finish - start;
            System.out.println("join time = " + time + " ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers() {
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();

        } finally {
            long finish = System.currentTimeMillis();
            long time = finish - start;
            System.out.println("findMembers time = " + time + " ms");
        }
    }

    public Optional<Member> findOne(Long memeberId) {
        return memberRepository.findById(memeberId);
    }
}
