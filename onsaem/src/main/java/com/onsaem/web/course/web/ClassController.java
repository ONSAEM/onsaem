package com.onsaem.web.course.web;

import java.util.List;
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
import com.onsaem.web.course.service.ClassVO;

/**
 * 작성자 - 주소현 작성 내용 - 클래스관리
 */

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

	// 클래스목록 페이지 이동 (강의목록, 인기강의목록)
	@RequestMapping(value = "/classList", method = RequestMethod.GET)
	public String classList(ClassInfoVO vo, Model model, Paging paging) {
		paging.setPageUnit(9);
		model.addAttribute("classList", classService.getClassInfoList(vo, paging).get("classList"));
		model.addAttribute("maxPrice", classService.classMaxPrice(vo));
		model.addAttribute("minPrice", classService.classMinPrice(vo));
		model.addAttribute("page", classService.getClassInfoList(vo, paging).get("newPaging"));
		return "content/course/classList";
	}

	// 클래스검색, 페이지, 정렬 처리(수정예정)
	@RequestMapping(value = "/classSearch", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> classSearch(ClassInfoVO vo, Model model, Paging paging) {
		paging.setPageUnit(9);
		return classService.getClassInfoList(vo, paging);
	}

	// 클래스상세 페이지 이동 (클래스정보, 미디어목록, 후기목록, 문의목록)
	@RequestMapping(value = "/classDetail", method = RequestMethod.GET)
	public String classDetail(ClassInfoVO vo, Model model, Authentication authentication) {
		if (authentication != null) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			model.addAttribute("like", classService.LikeCount(vo.getClassId(), userDetails.getUsername()));
		} else {
			model.addAttribute("like", classService.LikeCount(vo.getClassId(), null));
		}
		model.addAttribute("class", classService.getClassInfo(vo));
		Paging paging = new Paging();
		ReviewVO rvo = new ReviewVO();
		rvo.setGroupId(vo.getClassId());
		model.addAttribute("review", classReviewService.getReviewList(rvo, paging));
		QuestionVO qvo = new QuestionVO();
		qvo.setGroupId(vo.getClassId());
		model.addAttribute("question", classQueService.getQuestionList(qvo, paging));
		return "content/course/classDetail";
	}

	// 클래스 좋아요 추가
	@RequestMapping(value = "/addClassLike", method = RequestMethod.POST)
	@ResponseBody
	public int addClassLike(LikeVO vo) {
		return classService.addClassLike(vo);
	}

	// 클래스 좋아요 삭제
	@RequestMapping(value = "/delClassLike", method = RequestMethod.POST)
	@ResponseBody
	public int delClassLike(LikeVO vo) {
		return classService.delClassLike(vo);
	}

	// 클래스 신고
	@RequestMapping(value = "/insertReport", method = RequestMethod.POST)
	@ResponseBody
	public boolean insertReport(ReportVO vo) {
		return classService.insertReport(vo);
	}

	// 클래스 날짜 목록 가져오기
	@RequestMapping(value = "/getDateList", method = RequestMethod.GET)
	@ResponseBody
	public List<ClassVO> getDateList(ClassVO vo) {
		return classService.getDateList(vo);
	}

	// 해당 날짜 클래스일정 가져오기
	@RequestMapping(value = "/getclassList", method = RequestMethod.GET)
	@ResponseBody
	public List<ClassVO> getclassList(ClassVO vo) {
		return classService.getclassList(vo);
	}

	// 찜한 클래스 페이지 이동
	@RequestMapping(value = "/likeClass", method = RequestMethod.GET)
	public String likeClass(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		LikeVO vo = new LikeVO();
		vo.setMemberId(userDetails.getUsername());
		model.addAttribute("likeList", classService.getLikeList(vo));
		return "content/course/likeClass";
	}
	
	// 클래스 좋아요 전체삭제
	@RequestMapping(value = "/delAllLike", method = RequestMethod.POST)
	@ResponseBody
	public int delAllLike(LikeVO vo) {
		return classService.delAllLike(vo);
	}

	// 강사 클래스관리 페이지 이동
	@RequestMapping(value = "/classMGMT", method = RequestMethod.GET)
	public String courseMGMT(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		ClassInfoVO vo = new ClassInfoVO();
		vo.setMemberId(userDetails.getUsername());
		model.addAttribute("classList", classService.getMyClassInfoList(vo));
		return "content/course/classMGMT";
	}
	
	// 관리자 클래스 전체조회
	@RequestMapping(value = "/adminClass", method = RequestMethod.GET)
	public String adminClass(Model model) {
//		model.addAttribute("classList", classService.getAdminClassList(vo));
		return "content/course/adminClass";
	}
	
	//관리자 클래스 신청 전체조회
	// 관리자 클래스 전체조회
	@RequestMapping(value = "/adminClassApply", method = RequestMethod.GET)
	public String adminClassApply(Model model) {
//		model.addAttribute("classList", classService.getAdminCApplyList(vo));
		return "content/course/adminClassApply";
	}
	
	// 강의 등록
	
	// 강의 수정
	

}
