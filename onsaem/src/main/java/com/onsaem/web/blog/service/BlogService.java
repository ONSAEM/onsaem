package com.onsaem.web.blog.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.ReportVO;

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
	
	// 나를 구독한 사람들 조회
	List<LikeVO> subMeList(LikeVO likeVO);
	// 내가 구독한 사람들 조회
	List<LikeVO> mySubList(LikeVO likeVO);
	
	// 블로그 정보 변경
	public BlogVO updateInfo(MultipartFile[] headerFile, BlogVO vo) throws IllegalStateException, IOException;
	
	// 카테고리 등록
	public int cateInsert(CategoriesVO cateVO);
	// 카테고리 삭제
	public int cateDelete(String categoryId);
	
	// 관리자 신고조회
	List<ReportVO> blogReportList(ReportVO reportVO);
}
