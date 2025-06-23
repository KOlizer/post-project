package com.example.post_project;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PostPageInfo {
    private long totalCount; // 전체 게시글 수
    private long totalPage; // 전체 페이지 수
    private long currentPage; // 현재 페이지
    private int perPageNumber; // 페이지 게시글 수
    private long startIndex; // 현재 페이지의 시작 인덱스
    private long endIndex; // 현제 페이지의 끝 인덱스


    public PostPageInfo(long totalCount, long currentPage, int perPageNumber) {
        this.totalCount = totalCount;
        this.totalPage = (long) Math.ceil((double) totalCount / perPageNumber);
        this.currentPage = currentPage;
        this.perPageNumber = perPageNumber;
        this.startIndex = (currentPage - 1) * perPageNumber;
        this.endIndex = Math.min(startIndex + perPageNumber - 1, totalCount - 1);
    }
}
