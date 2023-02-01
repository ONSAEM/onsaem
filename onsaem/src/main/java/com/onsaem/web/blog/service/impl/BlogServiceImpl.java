package com.onsaem.web.blog.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.blog.mapper.BlogMapper;
import com.onsaem.web.blog.service.BlogService;
import com.onsaem.web.blog.service.BlogVO;
import com.onsaem.web.blog.service.CategoriesVO;
import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.ReportVO;
import com.onsaem.web.member.service.MemberVO;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired 
	BlogMapper blogMapper;
	
	@Autowired
	MediaService mediaService;
	
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

	@Override
	public BlogVO updateInfo(MultipartFile[] headerFile, BlogVO vo) throws IllegalStateException, IOException {
		// 블로그 정보 변경
			System.out.println(vo);
			int result = blogMapper.updateInfo(vo);
			if(headerFile != null) {
				MediaVO mvo = new MediaVO();
				mvo.setGroupId(vo.getBlogId());
				mvo.setGroups("블로그");
				mvo.setSubGroup("헤더이미지");
				mediaService.stopMedia(mvo);
				mediaService.uploadMedia(headerFile, mvo);
			}
			if (result > 0) {
				return vo;
			} else {
				return null;
			}
	}

	@Override
	public int cateInsert(CategoriesVO cateVO) {
		// 카테고리 등록
		return blogMapper.cateInsert(cateVO);
	}

	@Override
	public int cateDelete(String categoryId) {
		// 카테고리 삭제
		return blogMapper.cateDelete(categoryId);
	}

	@Override
	public List<ReportVO> blogReportList(ReportVO reportVO) {
		// 관리자 신고조회
		return blogMapper.blogReportList(reportVO);
	}

	@Override
	public int banUpdate(MemberVO memberVO) {
		// 관리자 제재기간 업데이트
		return blogMapper.banUpdate(memberVO);
	}

	@Override
	public int banStatusUpdate(ReportVO reportVO) {
		// 관리자 신고처리상태 업데이트
		return blogMapper.banStatusUpdate(reportVO);
	}
	
	
	
}
