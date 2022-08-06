package com.violet.springboot.web;

import com.violet.springboot.service.PostsService;
import com.violet.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        // mustache 스타터로 인해 컨트롤러에서 문자열을 반환할 떄 앞의 경로와 뒤의 파일 확장자가 자동으로 지정된다.
        // mustache 기본 위치 : src/main/resources/templates/ 에 두면 스프링 부트에서 mustache 파일 자동 로딩
        // 결과 >> src/main/resources/templates/index.mustache 로 전환되어 View Resolver가 처리
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
