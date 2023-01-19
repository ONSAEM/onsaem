package com.onsaem.web.blog.service;

import java.util.List;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.ReportVO;

public interface BlogWriteService {
		// 블로그 글 전체 조회
		public List<BlogWriteVO> getBlogList(BlogWriteVO blogWriteVO);
		// 블로그 단건 조회
		public List<BlogWriteVO> myBlog(String blogId);
		// 블로그 글 단건 조회
		public BlogWriteVO getBlog(String writeId);
		// 블로그 글 작성
		int blogInsert(BlogWriteVO blogWriteVO);

		// 블로그 글 수정
		int blogUpdate(BlogWriteVO blogWriteVO);
		// 블로그 글 삭제
		int blogDelete(BlogWriteVO blogWriteVO);
		
		// 좋아요 조회
		public String likeCount(LikeVO likeVO);
		// 좋아요 등록
		int addBlogLike(LikeVO likeVO);
		// 좋아요 취소
		int delBlogLike(LikeVO likeVO);
		// 좋아요 수
		public LikeVO cntBlogLike(LikeVO likeVO);
		
		// 해당 유저의 카테고리 조회
		public List<CategoriesVO> cateList(CategoriesVO categoriesVO);
		
		// 해당 유저의 최신글 3개 조회
		public List<BlogWriteVO> recentWrite(String blogId);
		
		// 해당 유저의 검색 목록 조회
		public List<BlogWriteVO> searchWrite(String blogId, String data);
		
		// 신고
		int addBan(ReportVO reportVO);
}
