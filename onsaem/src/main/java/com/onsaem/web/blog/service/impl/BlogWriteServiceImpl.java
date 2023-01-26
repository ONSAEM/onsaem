package com.onsaem.web.blog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.blog.mapper.BlogWriteMapper;
import com.onsaem.web.blog.service.BlogWriteService;
import com.onsaem.web.blog.service.BlogWriteVO;
import com.onsaem.web.blog.service.CategoriesVO;
import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.ReportVO;
import com.onsaem.web.course.service.ClassInfoVO;

@Service
public class BlogWriteServiceImpl implements BlogWriteService {
	
	@Autowired BlogWriteMapper blogWriteMapper;
	
	
	@Override
	public Map<String, Object> getBlogPageList(BlogWriteVO blogWriteVO, Paging paging, String blogId) {
		Paging newPaging = blogWriteMapper.blogCount(blogWriteVO);
		newPaging.setPageUnit(paging.getPageUnit());
		newPaging.setPage(paging.getPage());
		newPaging.setTotalRecord(newPaging.getTotalRecord());
		blogWriteVO.setFirst(newPaging.getFirst());
		blogWriteVO.setLast(newPaging.getLast());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("blogList", blogWriteMapper.myBlog(blogId));
		result.put("newPaging", newPaging);
		return result;
	}
	
	
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
	public String likeCount(LikeVO likeVO) {
		// 좋아요 조회
		return blogWriteMapper.likeCount(likeVO);
	}

	@Override
	public LikeVO cntBlogLike(LikeVO likeVO) {
		// 좋아요 수
		return blogWriteMapper.cntBlogLike(likeVO);
	}

	@Override
	public List<CategoriesVO> cateList(CategoriesVO categoriesVO) {
		// 카테고리 조회
		return blogWriteMapper.cateList(categoriesVO);
	}

	@Override
	public List<BlogWriteVO> recentWrite(String blogId) {
		// 해당 유저의 최신 글 3개 조회
		return blogWriteMapper.recentWrite(blogId);
	}

	@Override
	public List<BlogWriteVO> searchWrite(String blogId, String data) {
		// 해당 유저의 검색 목록 조회
		return blogWriteMapper.searchWrite(blogId, data);
	}

	@Override
	public int addBan(ReportVO reportVO) {
		// 신고
		return blogWriteMapper.addBan(reportVO);
	}


	

}
