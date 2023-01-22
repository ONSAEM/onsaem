package com.onsaem.web.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.blog.mapper.BlogMapper;
import com.onsaem.web.blog.service.BlogService;
import com.onsaem.web.blog.service.BlogVO;
import com.onsaem.web.common.service.LikeVO;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired BlogMapper blogMapper;
	
	@Override
	public List<BlogVO> taskList(BlogVO blogVO) {
		// 전체 조회
		return blogMapper.taskList(blogVO);
	}

	@Override
	public BlogVO getBlogInfo(String blogId) {
		// 단건 조회
		return blogMapper.getBlogInfo(blogId);
	}

	@Override
	public String subCount(LikeVO likeVO) {
		// 구독 조회
		return blogMapper.subCount(likeVO);
	}

	@Override
	public int addSub(LikeVO likeVO) {
		// 구독 추가
		return blogMapper.addSub(likeVO);
	}

	@Override
	public int delSub(LikeVO likeVO) {
		// 구독 삭제
		return blogMapper.delSub(likeVO);
	}

	@Override
	public List<LikeVO> subMeList(LikeVO likeVO) {
		// 나를 구독한 사람들 조회
		return blogMapper.subMeList(likeVO);
	}

	@Override
	public List<LikeVO> mySubList(LikeVO likeVO) {
		// 내가 구독한 사람들 조회
		return blogMapper.mySubList(likeVO);
	}

}
