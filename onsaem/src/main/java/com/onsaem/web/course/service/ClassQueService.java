package com.onsaem.web.course.service;

import java.util.Map;

import com.onsaem.web.common.service.QuestionVO;
import com.onsaem.web.common.service.Paging;

public interface ClassQueService {

	// 문의 전체조회
	public Map<String, Object> getQuestionList(QuestionVO vo, Paging paging);

	// 문의 단건조회
	public QuestionVO getQuestion(QuestionVO vo);

	// 문의 갯수 조회
	public int questionCount(QuestionVO vo);

	// 문의 등록
	public QuestionVO insertQuestion(QuestionVO vo);

	// 문의 수정
	public boolean updateQuestion(QuestionVO vo);

	// 답변 등록,수정
	public boolean updateAnswer(QuestionVO vo);
}
