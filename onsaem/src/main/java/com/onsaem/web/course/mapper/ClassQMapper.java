package com.onsaem.web.course.mapper;

import java.util.List;

import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.QuestionVO;

public interface ClassQMapper {
	// 문의 전체조회
	public List<QuestionVO> getQuestionList(QuestionVO vo);

	// 문의 단건조회
	public QuestionVO getQuestion(QuestionVO vo);

	// 문의 갯수 조회
	public Paging questionCount(QuestionVO vo);
}
