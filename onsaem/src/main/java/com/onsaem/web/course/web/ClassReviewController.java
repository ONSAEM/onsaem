package com.onsaem.web.course.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.ReviewVO;
import com.onsaem.web.course.service.ClassReviewService;

/**
 * 작성자 - 주소현
 * 작성 내용 - 클래스리뷰관리
 */


@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/class")
public class ClassReviewController {
	
	@Autowired
	ClassReviewService classReviewService;
	
	// 리뷰페이지 처리
	@RequestMapping(value = "/newRList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> newRList(ReviewVO vo, Paging paging) {
		return classReviewService.getReviewList(vo, paging);
	}
	
	// 리뷰 상세조회
	@RequestMapping(value = "/getReview", method = RequestMethod.GET)
	@ResponseBody
	public ReviewVO getReview(ReviewVO vo) {
		return classReviewService.getReview(vo);
	}

}
