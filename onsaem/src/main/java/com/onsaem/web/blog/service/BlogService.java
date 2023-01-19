package com.onsaem.web.blog.service;

import java.util.List;

import com.onsaem.web.common.service.LikeVO;

public interface BlogService {
	
	// 블로그 관리 전체 조회
	List<BlogVO> taskList(BlogVO blogVO);
	// 블로그 관리 단건 조회
	public BlogVO getBlogInfo(String blogId);
	
	// 구독 조회
	public String subCount(LikeVO likeVO);
	// 구독 추가
	int addSub(LikeVO likeVO);
	// 구독 삭제
	int delSub(LikeVO likeVO);
}
