package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    /* 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리
    * 스프링 부트 템플릿 엔진 기본 viewName 매핑
    * resoureces:templates/(ViewName)/.html */

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        // Controller 에너테이션이 달려있으므로 html 파일명을 return 하도록 해야 함 (templates 폴더 안에 있어야 함)
        return "hello";
    }

    @GetMapping("hello-mvc") // required는 기본값이 true임
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
    
    @GetMapping("hello-string")
    @ResponseBody // body 부분에 직접 data를 넣어주겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring" 뷰 이런거 없고 문자 자체가 날라감
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        // json 방식으로 return 하게 됨
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName (String name) {
            this.name = name;
        }
    }
}
