package hello.spring.service;

import hello.spring.domain.Member;
import hello.spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);
        Member findMember = memberService.findOne(saveId).get();

        //then
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void validateDuplicateName() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> memberService.join(member2))
                .withMessage("이미 존재하는 회원입니다.");
    }

    @Test
    void findOne() {
        //when
        Member member = memberService.findOne(1L).get();

        //then
        assertThat(member.getName()).isEqualTo("JPA");
    }
}
