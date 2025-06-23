package com.example.post_project.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
public class Post {
    private long postId;
    private String postType;
    private String postTitle;
    private String postContent;
    private LocalDateTime postCreatedAt;
    private LocalDateTime postEditAt;
    private long postAuthorId;
    private String postImage;
    private boolean postDeleted;

}
