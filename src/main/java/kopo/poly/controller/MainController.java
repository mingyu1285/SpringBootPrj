package kopo.poly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@Slf4j
public class MainController {

    @GetMapping("/main")
    public String main() throws Exception{
        log.info(this.getClass().getName() + ".main 페이지 보여주는 함수 실행");
        return "/main";
    }

    @GetMapping("/introducemyself")
    public String introduceMyself() throws Exception{
        log.info(this.getClass().getName() + ".소개 페이지 실행");
        return "introducemyself"; // introducemyself.html 또는 introducemyself.jsp와 같은 뷰 이름을 반환합니다.
    }

}
