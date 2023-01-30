package com.onsaem.web.course.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.QuestionVO;
import com.onsaem.web.course.service.ClassQueService;

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

	// 문의 등록
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
}
