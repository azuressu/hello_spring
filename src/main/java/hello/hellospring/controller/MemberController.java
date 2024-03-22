package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/* 해당 에너테이션을 사용하는 경우, 스프링 빈으로 자동 주입하게 됨 */
@Controller
public class MemberController {

    /* 스프링 빈을 등록하는 2 가지 방법
    * 1. 컴포넌트 스캔과 자동 의존관계 설정
    * 2. 자바 코드로 직접 스프링 빈 등록하기 */

    /* 컴포넌트 스캔과 자동 의존관계 설정
    * @Component 애너테이션이 있으면 스프링 빈으로 자동 등록
    * @Controller 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문
    *
    * @Component 를 포함하는 다음 에너테이셔도 스프링 빈으로 자동 등록됨
    * @Controller
    * @Service
    * @Repository */

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        System.out.println("member = " + member.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
