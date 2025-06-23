package com.example.post_project.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
public class User {
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPasswd;
    private LocalDateTime registDate;
}
