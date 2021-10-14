package hello.spring;

import hello.spring.repository.JdbcMemberRepository;
import hello.spring.repository.MemberRepository;
import hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // DataSource : 데이터베이스 커넥션을 획득할 때 사용하는 객체
    // 스프링 부트에서 자동으로 application.properties 의 데이터 설정 정보를 읽어서 DataSource 생성하고 스프링 빈으로 만들어준다
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
