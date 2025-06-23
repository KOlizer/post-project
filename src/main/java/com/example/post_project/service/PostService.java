package com.example.post_project.service;

import com.example.post_project.PostPageDTO;
import com.example.post_project.mapper.PostMapper;
import com.example.post_project.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    void savePost(Post post);

    Post getPostById(Long postId);

    List<Post> getPostsByType(String postType);

    void updatePost(Post post);

    PostPageDTO getPostByTypeWithPaging(String postType, long currentPage, int perPageNumber);
}
