package com.onsaem.web.course.mapper;

import java.util.List;

import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.ReviewVO;

public interface ClassReviewMapper {
	// 리뷰 전체조회
	public List<ReviewVO> getReviewList(ReviewVO vo);

	// 리뷰 단건조회
	public ReviewVO getReview(ReviewVO vo);

	// 리뷰 갯수 조회
	public Paging reviewCount(ReviewVO vo);

	// 평균별점 조회
	public int starAvg(ReviewVO vo);
}
