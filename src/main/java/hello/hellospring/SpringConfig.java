package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    /**
     * 개방-폐쇄 원칙(OCP, Open-Closed Principle)
     * 확장에는 열려있고, 수정, 변경에는 닫혀있다.
     *
     * 스프링의 DI(Dependencies Injection)을 사용하면 기존 코드를 전혀 손대지 않고,
     * 설정만으로 구현 클래스를 변경할 수 있다.
     * 회원을 등록하고 DB에 결과가 잘 입력되는지 확인하고,
     * 데이터를 DB에 저장하므로 스프링 서버를 다시 실행해도 데이터가 안전하게 저장된다.
     */

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
