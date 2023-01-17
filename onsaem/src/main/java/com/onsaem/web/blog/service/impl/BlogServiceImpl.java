package com.onsaem.web.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.blog.mapper.BlogMapper;
import com.onsaem.web.blog.service.BlogService;
import com.onsaem.web.blog.service.BlogVO;

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

}
