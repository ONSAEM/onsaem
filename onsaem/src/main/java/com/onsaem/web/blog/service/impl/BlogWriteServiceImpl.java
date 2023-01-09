package com.onsaem.web.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.blog.mapper.BlogWriteMapper;
import com.onsaem.web.blog.service.BlogLikeVO;
import com.onsaem.web.blog.service.BlogMediaVO;
import com.onsaem.web.blog.service.BlogReportVO;
import com.onsaem.web.blog.service.BlogWriteService;
import com.onsaem.web.blog.service.BlogWriteVO;

@Service
public class BlogWriteServiceImpl implements BlogWriteService {
	
	@Autowired BlogWriteMapper blogWriteMapper;
	@Override
	public List<BlogWriteVO> getBlogList(BlogWriteVO blogWriteVO) {
		// 전체 조회
		return blogWriteMapper.getBlogList(blogWriteVO);
	}
	
	@Override
	public BlogWriteVO myBlog(String blogId) {
		// 블로그 단건조회
		return blogWriteMapper.myBlog(blogId);
	}

	@Override
	public BlogWriteVO getBlog(String blogId, String writeId) {
		// 블로그 글 단건 조회
		return blogWriteMapper.getBlog(blogId, writeId);
	}

	@Override
	public int blogInsert(BlogWriteVO blogWriteVO) {
		// 글 작성
		return blogWriteMapper.blogInsert(blogWriteVO);
	}

	@Override
	public int mediaInsert(BlogMediaVO mediaVO) {
		// 미디어 첨부
		return 0;
	}

	@Override
	public int blogUpdate(BlogWriteVO blogWriteVO) {
		// 글 수정
		return blogWriteMapper.blogUpdate(blogWriteVO);
	}

	@Override
	public int blogDelete(BlogWriteVO blogWriteVO) {
		// 글 삭제
		return blogWriteMapper.blogDelete(blogWriteVO);
	}

	@Override
	public int likeInsert(BlogLikeVO likeVO) {
		// 좋아요
		return 0;
	}

	@Override
	public int likeDelete(BlogLikeVO likeVO) {
		// 좋아요 취소
		return 0;
	}

	@Override
	public int reportInsert(BlogReportVO blogReportVO) {
		// 신고
		return 0;
	}

	

}
