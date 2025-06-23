package com.example.post_project.controller;

import com.example.post_project.post.Post;
import com.example.post_project.service.PostService;
import com.example.post_project.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/newPost")
    public String showSavePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/post-form";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute("post") Post post,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {

        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/auth/login";
        }

        post.setPostAuthorId(loginUser.getUserId());

        postService.savePost(post);

        redirectAttributes.addFlashAttribute("saveMsg", "게시글이 성공적으로 저장되었습니다!");
        return "redirect:/";
    }

    @GetMapping("/{postId}")
    public String showPost(@PathVariable("postId") Long postId,
                           Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        return "post/post-detail";
    }

    @GetMapping("/edit/{postId}")
    public String editPost(@PathVariable("postId") Long postId,
                           HttpSession session,
                           Model model) {
        Post findPost = postService.getPostById(postId);
        User loginUser = (User) session.getAttribute("loginUser");

        if (findPost == null || loginUser == null ||
                !loginUser.getUserId().equals(findPost.getPostAuthorId())) {
            return "redirect:/post/" + postId;
        }

        model.addAttribute("post", findPost);
        return "post/post-edit";
    }

    /**
     * @author : kim
     * @method : post
     */
    @PostMapping("/edit/{postId}")
    public String editPost(@PathVariable("postId") Long postId,
                           @ModelAttribute("post") Post post,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {

        Post findPost = postService.getPostById(postId);
        findPost.setPostTitle(post.getPostTitle());
        findPost.setPostContent(post.getPostContent());

        postService.updatePost(findPost);

        redirectAttributes.addFlashAttribute("successMessage", "게시글이 수정되었습니다!");
        return "redirect:/post/" + postId;
    }

    @PostMapping("/delete/{postId}")
    public String deletePost(@PathVariable("postId") Long postId) {
        Post findPost = postService.getPostById(postId);

        findPost.setPostDeleted(true);
        postService.updatePost(findPost);
        return "redirect:/";
    }
}
