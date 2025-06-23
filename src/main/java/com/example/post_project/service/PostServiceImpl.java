package com.example.post_project.service;

import lombok.extern.slf4j.Slf4j;

import com.example.post_project.PostPageDTO;
import com.example.post_project.PostPageInfo;
import com.example.post_project.mapper.PostMapper;
import com.example.post_project.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;

    @Override
    public void savePost(Post post) {
        postMapper.savePost(post);
    }

    @Override
    public Post getPostById(Long postId) {
        Post post = postMapper.findPostById(postId);
        return post;
    }

    @Override
    public List<Post> getPostsByType(String postType) {
        List<Post> posts = postMapper.findPostsByType(postType);
        return posts;
    }

    @Override
    public void updatePost(Post post) {
        postMapper.updatePost(post);
    }

    // 인덱싱
    public PostPageDTO getPostByTypeWithPaging(String postType, long currentPage, int perPageNumber) {
        // 전체 게시글 수 조회
        long totalPageCount = postMapper.countPostsByType(postType);

        // PostPageInfo 생성, 인덱스 계산
        PostPageInfo postPageInfo = new PostPageInfo(totalPageCount, currentPage, perPageNumber);

        // 페이징된 게시글 목록 조회
        List<Post> posts = postMapper.findPostsByTypeWithPageing(postType,
                                                                postPageInfo.getStartIndex(),
                                                                postPageInfo.getPerPageNumber()
                                                                );

        // 결과 반환 (paveDTO = posts + postPageInfo)
        return new PostPageDTO(posts, postPageInfo);
    }



}
