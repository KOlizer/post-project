package com.example.post_project.mapper;

import com.example.post_project.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void saveUser(User user);

    User findByUserEmail(String email);
}
