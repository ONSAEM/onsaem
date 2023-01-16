package com.onsaem.web.blog.service;

import java.util.List;

public interface BlogService {
	
	// 블로그 관리 전체 조회
	List<BlogVO> taskList(BlogVO blogVO);
	// 블로그 관리 단건 조회
	public BlogVO task(String blogId);
}
