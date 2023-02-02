package com.onsaem.web.course.mapper;

import java.util.List;

import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.QuestionVO;

public interface ClassQueMapper {
	// 문의 전체조회
	public List<QuestionVO> getQuestionList(QuestionVO vo);

	// 문의 단건조회
	public QuestionVO getQuestion(QuestionVO vo);

	// 문의 갯수 조회
	public Paging questionCount(QuestionVO vo);

	// 문의 등록
	public int insertQuestion(QuestionVO vo);

	// 문의 수정
	public int updateQuestion(QuestionVO vo);

	// 답변 등록,수정
	public int updateAnswer(QuestionVO vo);

	// 문의 전체조회
	public List<QuestionVO> getMyQuestionList(QuestionVO vo);
}
