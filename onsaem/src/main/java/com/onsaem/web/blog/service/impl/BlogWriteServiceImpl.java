package com.onsaem.web.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.blog.mapper.BlogWriteMapper;
import com.onsaem.web.blog.service.BlogMediaVO;
import com.onsaem.web.blog.service.BlogReportVO;
import com.onsaem.web.blog.service.BlogWriteService;
import com.onsaem.web.blog.service.BlogWriteVO;
import com.onsaem.web.common.service.LikeVO;

@Service
public class BlogWriteServiceImpl implements BlogWriteService {
	
	@Autowired BlogWriteMapper blogWriteMapper;
	@Override
	public List<BlogWriteVO> getBlogList(BlogWriteVO blogWriteVO) {
		// 전체 조회
		return blogWriteMapper.getBlogList(blogWriteVO);
	}
	
	@Override
	public List<BlogWriteVO> myBlog(String blogId) {
		// 블로그 단건조회
		return blogWriteMapper.myBlog(blogId);
	}

	@Override
	public BlogWriteVO getBlog(String writeId) {
		// 블로그 글 단건 조회
		return blogWriteMapper.getBlog(writeId);
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
	public int addBlogLike(LikeVO likeVO) {
		// 좋아요 등록
		return blogWriteMapper.addBlogLike(likeVO);
	}

	@Override
	public int delBlogLike(LikeVO likeVO) {
		// 좋아요 취소
		return blogWriteMapper.delBlogLike(likeVO);
	}

	@Override
	public int reportInsert(BlogReportVO blogReportVO) {
		// 신고
		return 0;
	}

	@Override
	public String likeCount(LikeVO likeVO) {
		// 좋아요 조회
		return blogWriteMapper.likeCount(likeVO);
	}

	@Override
	public LikeVO cntBlogLike(LikeVO likeVO) {
		// 좋아요 수
		return blogWriteMapper.cntBlogLike(likeVO);
	}

	

}
