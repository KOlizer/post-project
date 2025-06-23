package com.example.post_project.service;

import com.example.post_project.mapper.UserMapper;
import com.example.post_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void regist(User user) {
        String rawPasswd = user.getUserPasswd();
        String hashedPasswd = passwordEncoder.encode(rawPasswd);
        user.setUserPasswd(hashedPasswd);

        userMapper.saveUser(user);
    }

    @Override
    public User login(String userEmail, String password) {
        User user = userMapper.findByUserEmail(userEmail);
        if (user == null) {
            throw new IllegalArgumentException("등록된 사용자가 없습니다.");
        }
        if (!passwordEncoder.matches(password, user.getUserPasswd())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
}
