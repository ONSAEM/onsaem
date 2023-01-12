package com.onsaem.web.blog.mapper;

import java.util.List;

import com.onsaem.web.blog.service.BlogMediaVO;
import com.onsaem.web.blog.service.BlogReportVO;
import com.onsaem.web.blog.service.BlogWriteVO;
import com.onsaem.web.common.service.LikeVO;

public interface BlogWriteMapper {
	// 블로그 글 전체 조회
	public List<BlogWriteVO> getBlogList(BlogWriteVO blogWriteVO);
	// 블로그 단건 조회
	public List<BlogWriteVO> myBlog(String blogId);
	// 블로그 글 단건 조회
	public BlogWriteVO getBlog(String writeId);
	// 블로그 글 작성
	int blogInsert(BlogWriteVO blogWriteVO);
	// 블로그 미디어 첨부
	int mediaInsert(BlogMediaVO mediaVO);
	// 블로그 글 수정
	int blogUpdate(BlogWriteVO blogWriteVO);
	// 블로그 글 삭제
	int blogDelete(BlogWriteVO blogWriteVO);
	// 좋아요 조회
	public String likeCount(LikeVO likeVO);
	// 좋아요
	int addBlogLike(LikeVO likeVO);
	// 좋아요 취소
	int delBlogLike(LikeVO likeVO);
	// 좋아요 수
	public LikeVO cntBlogLike(LikeVO likeVO);
	
	// 신고
	int reportInsert(BlogReportVO blogReportVO);
}
