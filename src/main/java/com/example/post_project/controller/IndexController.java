package com.example.post_project.controller;

import static java.util.stream.Collectors.toList;

import com.example.post_project.PostPageDTO;
import com.example.post_project.PostPageInfo;
import com.example.post_project.post.Post;
import com.example.post_project.service.PostService;
import com.example.post_project.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;

    @GetMapping("/")
    public String home(
            @RequestParam(value = "postType", defaultValue = "free") String postType,
            @RequestParam(value = "page", defaultValue = "1") long currentPage,
            Model model,
            HttpSession session) {

        // 한 페이지 당 노출 게시글 개수
        int perPageNumber = 10;

        // 로그인 사용자 정보
        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        // DTO 생성
        PostPageDTO postPageDTO = postService.getPostByTypeWithPaging(postType, currentPage, 10);

        // DTO 정보 전달
        model.addAttribute("currentType", postType);
        model.addAttribute("posts", postPageDTO.getPosts());
        model.addAttribute("postPageInfo", postPageDTO.getPostPageInfo());

        return "index";
    }
}
