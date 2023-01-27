package com.onsaem.web.course.service;

import java.util.Map;

import com.onsaem.web.common.service.QuestionVO;
import com.onsaem.web.common.service.Paging;

public interface ClassQService {
	
	// 문의 전체조회
	public Map<String, Object> getQuestionList(QuestionVO vo, Paging paging);

	// 문의 단건조회
	public QuestionVO getQuestion(QuestionVO vo);

	// 문의 갯수 조회
	public Paging questionCount(QuestionVO vo);
}
