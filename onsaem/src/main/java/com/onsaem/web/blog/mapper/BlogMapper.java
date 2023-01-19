package com.onsaem.web.blog.mapper;

import java.util.List;

import com.onsaem.web.blog.service.BlogVO;
import com.onsaem.web.common.service.LikeVO;

public interface BlogMapper {
	// 블로그 관리
	// 블로그 관리 전체 조회
		List<BlogVO> taskList(BlogVO blogVO);
	// 블로그 관리 단건 조회
		public BlogVO getBlogInfo(String blogId);
	// 블로그 관리 내용 작성
	
	// 블로그 관리 내용 수정
	
	// 통계
	// 유입 시간 조회
	
	// 유입 경로 조회
	
	// 유입 키워드 조회
	
	// 구독 조회
	public String subCount(LikeVO likeVO);
	// 구독 추가
	int addSub(LikeVO likeVO);
	// 구독 삭제
	int delSub(LikeVO likeVO);
	// 전체 조회
		
	// 삭제
	
	// 카테고리
	// 전체 조회
	
	// 카테고리 등록
	
	// 카테고리 삭제
	
	
	// 신고
	// 신고 등록
	
	
}
