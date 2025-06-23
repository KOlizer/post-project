package com.example.post_project.mapper;

import com.example.post_project.post.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    void savePost(Post post);

    Post findPostById(Long postId);

    List<Post> findPostsByType(String postType);

    void updatePost(Post post);


    Long countPostsByType(String postType);
    List<Post> findPostsByTypeWithPageing(@Param("postType") String postType,
                                          @Param("startIndex") long startIndex,
                                          @Param("perPageNumber") int perPageNumber);
}