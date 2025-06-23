package com.example.post_project.controller;

import com.example.post_project.service.UserService;
import com.example.post_project.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "auth/login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute("user") User user,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        User loginUser = userService.login(user.getUserEmail(), user.getUserPasswd());
        session.setAttribute("loginUser", loginUser);
        redirectAttributes.addFlashAttribute("loginMsg", "로그인 성공 ! " + loginUser.getUserName() + "님 환영합니다");
        return("redirect:/");
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/regist")
    public String regist(Model model) {
        model.addAttribute("user", new User());
        return "auth/regist";
    }


    @PostMapping("/regist")
    public String regist(@ModelAttribute("user") User user,
                          RedirectAttributes redirectAttributes) {
        userService.regist(user);
        redirectAttributes.addFlashAttribute("msg", "회원가입이 완료되었습니다!");
        return "redirect:/auth/login";
    }


}
