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

import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.QuestionVO;
import com.onsaem.web.course.service.ClassInfoVO;
import com.onsaem.web.course.service.ClassQueService;

/**
 * 작성자 - 주소현 작성 내용 - 클래스문의관리
 */

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/class")
public class ClassQueController {

	@Autowired
	ClassQueService classQueService;

	// 문의 등록
	@RequestMapping(value = "/insertQue", method = RequestMethod.POST)
	@ResponseBody
	public QuestionVO insertQue(QuestionVO vo) {
		return classQueService.insertQuestion(vo);
	}

	// 문의 상세조회
	@RequestMapping(value = "/getQue", method = RequestMethod.GET)
	@ResponseBody
	public QuestionVO getQue(QuestionVO vo) {
		return classQueService.getQuestion(vo);
	}

	// 문의 수정
	@RequestMapping(value = "/updateQue", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateQue(QuestionVO vo) {
		return classQueService.updateQuestion(vo);
	}

	// 답변 등록,수정
	@RequestMapping(value = "/updateAnswer", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateAnswer(QuestionVO vo) {
		return classQueService.updateAnswer(vo);
	}

	// 문의페이지 처리
	@RequestMapping(value = "/newQList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> newQList(QuestionVO vo, Paging paging) {
		return classQueService.getQuestionList(vo, paging);
	}

	// 회원 문의조회 페이지 이동
	@RequestMapping(value = "/myQue", method = RequestMethod.GET)
	public String myQue(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		QuestionVO vo = new QuestionVO();
		vo.setWriterId(userDetails.getUsername());
		Paging paging = new Paging();
		model.addAttribute("question", classQueService.getMyQuestionList(vo, paging));
		return "content/course/myQue";
	}

	// 강사 문의관리 페이지 이동
	@RequestMapping(value = "/queMGMT", method = RequestMethod.GET)
	public String queMGMT(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		QuestionVO vo = new QuestionVO();
		vo.setMemberId(userDetails.getUsername());
		model.addAttribute("queList", classQueService.getMyClassQueList(vo));
		return "content/course/queMGMT";
	}
}
