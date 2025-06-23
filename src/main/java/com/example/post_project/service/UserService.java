package com.example.post_project.service;

import com.example.post_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void regist(User user);

    User login(String userEmail, String userPasswd);
}
