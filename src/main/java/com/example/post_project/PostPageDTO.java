package com.example.post_project;

import com.example.post_project.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PostPageDTO {
    private List<Post> posts;
    private PostPageInfo postPageInfo;

    public PostPageDTO(List<Post> posts, PostPageInfo postPageInfo) {
        this.posts = posts;
        this.postPageInfo = postPageInfo;
    }

}
