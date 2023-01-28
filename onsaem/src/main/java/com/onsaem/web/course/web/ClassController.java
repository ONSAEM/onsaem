package com.onsaem.web.course.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.QuestionVO;
import com.onsaem.web.common.service.ReportVO;
import com.onsaem.web.common.service.ReviewVO;
import com.onsaem.web.course.service.ClassInfoVO;
import com.onsaem.web.course.service.ClassQueService;
import com.onsaem.web.course.service.ClassReviewService;
import com.onsaem.web.course.service.ClassService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/class")
public class ClassController {

	@Autowired
	ClassService classService;

	@Autowired
	ClassReviewService classReviewService;

	@Autowired
	ClassQueService classQueService;

	// 강의목록 페이지 이동 (강의목록, 인기강의목록)
	@RequestMapping(value = "/classList", method = RequestMethod.GET)
	public String classList(ClassInfoVO vo, Model model, Paging paging) {
		paging.setPageUnit(9);
		model.addAttribute("classList", classService.getClassList(vo, paging).get("classList"));
		model.addAttribute("maxPrice", classService.classMaxPrice(vo));
		model.addAttribute("minPrice", classService.classMinPrice(vo));
		model.addAttribute("page", classService.getClassList(vo, paging).get("newPaging"));
		return "content/course/classList";
	}

	// 강의검색, 페이지, 정렬 처리(수정예정)
	@RequestMapping(value = "/classSearch", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> classSearch(ClassInfoVO vo, Model model, Paging paging) {
		paging.setPageUnit(9);
		return classService.getClassList(vo, paging);
	}

	// 강의상세 페이지 이동 (강의정보, 미디어목록, 후기목록, 문의목록)
	@RequestMapping(value = "/classDetail", method = RequestMethod.GET)
	public String classDetail(ClassInfoVO vo, Model model, Authentication authentication) {
		if (authentication != null) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			model.addAttribute("like", classService.LikeCount(vo.getClassId(), userDetails.getUsername()));
		} else {
			model.addAttribute("like", classService.LikeCount(vo.getClassId(), null));
		}
		model.addAttribute("class", classService.getClass(vo));
		System.out.println(model.getAttribute("class"));
		model.addAttribute("mediaList", classService.classMediaList(vo));
		Paging paging = new Paging();
		paging.setPage(1);
		ReviewVO rvo = new ReviewVO();
		rvo.setGroupId(vo.getClassId());
		model.addAttribute("review", classReviewService.getReviewList(rvo, paging));
		QuestionVO qvo = new QuestionVO();
		qvo.setGroupId(vo.getClassId());
		model.addAttribute("question", classQueService.getQuestionList(qvo, paging));
		return "content/course/classDetail";
	}

	// 예약결제 페이지 이동
	@RequestMapping(value = "/booking", method = RequestMethod.GET)
	public String booking(ClassInfoVO vo, Model model) {
		return "content/course/booking";
	}

	// 예약완료 페이지 이동
	@RequestMapping(value = "/bookingCOM", method = RequestMethod.GET)
	public String bookingCOM(ClassInfoVO vo, Model model) {
		return "content/course/bookingCOM";
	}

	// 강의관리 페이지 이동
	@RequestMapping(value = "/classMGMT", method = RequestMethod.GET)
	public String courseMGMT(ClassInfoVO vo, Model model) {
		return "content/course/classMGMT";
	}

	// 강의 좋아요 추가
	@RequestMapping(value = "/addClassLike", method = RequestMethod.POST)
	@ResponseBody
	public int addClassLike(LikeVO vo) {
		return classService.addClassLike(vo);
	}

	// 강의 좋아요 삭제
	@RequestMapping(value = "/delClassLike", method = RequestMethod.POST)
	@ResponseBody
	public int delClassLike(LikeVO vo) {
		return classService.delClassLike(vo);
	}

	// 강의 신고
	@RequestMapping(value = "/insertReport", method = RequestMethod.POST)
	@ResponseBody
	public boolean insertReport(ReportVO vo) {
		return classService.insertReport(vo);
	}
}
