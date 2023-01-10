package com.onsaem.web.blog.service;

import java.util.List;

public interface BlogWriteService {
		// 블로그 글 전체 조회
		public List<BlogWriteVO> getBlogList(BlogWriteVO blogWriteVO);
		// 블로그 단건 조회
		public List<BlogWriteVO> myBlog(String blogId);
		// 블로그 글 단건 조회
		public BlogWriteVO getBlog(String blogId, String writeId);
		// 블로그 글 작성
		int blogInsert(BlogWriteVO blogWriteVO);
		// 블로그 미디어 첨부
		int mediaInsert(BlogMediaVO mediaVO);
		// 블로그 글 수정
		int blogUpdate(BlogWriteVO blogWriteVO);
		// 블로그 글 삭제
		int blogDelete(BlogWriteVO blogWriteVO);
		// 좋아요
		int likeInsert(BlogLikeVO likeVO);
		// 좋아요 취소
		int likeDelete(BlogLikeVO likeVO);
		
		// 신고
		int reportInsert(BlogReportVO blogReportVO);
}
