package com.onsaem.web.course.service;

import java.util.Map;

import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.ReviewVO;

public interface ClassReviewService {
	// 리뷰 전체조회
	public Map<String, Object> getReviewList(ReviewVO vo, Paging paging);

	// 리뷰 단건조회
	public ReviewVO getReview(ReviewVO vo);

	// 리뷰 갯수 조회
	public int reviewCount(ReviewVO vo);

	// 평균별점 조회
	public int starAvg(ReviewVO vo);

	// 리뷰 등록
	public ReviewVO insertReview(ReviewVO vo);

	// 리뷰 수정
	public boolean updateReview(ReviewVO vo);
}
