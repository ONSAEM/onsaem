package com.onsaem.web.course.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.common.service.QuestionVO;
import com.onsaem.web.common.service.ReviewVO;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.course.mapper.ClassQMapper;
import com.onsaem.web.course.service.ClassInfoVO;
import com.onsaem.web.course.service.ClassQService;

@Service
public class ClassQServiceImpl implements ClassQService{
	
	@Autowired
	ClassQMapper classQMapper;
	
	@Override
	public Map<String, Object> getQuestionList(QuestionVO qvo, Paging paging) {
		Paging newPaging = classQMapper.questionCount(qvo);
		newPaging.setPage(paging.getPage());
		newPaging.setTotalRecord(newPaging.getTotalRecord());
		qvo.setFirst(newPaging.getFirst());
		qvo.setLast(newPaging.getLast());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("qList", classQMapper.getQuestionList(qvo));
		result.put("qPaging", newPaging);
		return result;
	}

	@Override
	public QuestionVO getQuestion(QuestionVO vo) {
		
		return null;
	}

	@Override
	public Paging questionCount(QuestionVO vo) {
		
		return null;
	}

}
