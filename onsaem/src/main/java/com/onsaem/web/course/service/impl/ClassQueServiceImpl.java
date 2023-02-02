package com.onsaem.web.course.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.common.service.QuestionVO;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.course.mapper.ClassQueMapper;
import com.onsaem.web.course.service.ClassQueService;
import com.onsaem.web.member.service.MemberService;

@Service
public class ClassQueServiceImpl implements ClassQueService{
	
	@Autowired
	ClassQueMapper classQMapper;
	
	@Autowired
	MediaService mediaService;
	
	@Autowired
	MemberService memberService;
	
	@Override
	public Map<String, Object> getQuestionList(QuestionVO qvo, Paging paging) {
		Paging newPaging = classQMapper.questionCount(qvo);
		newPaging.setPage(paging.getPage());
		newPaging.setTotalRecord(newPaging.getTotalRecord());
		qvo.setFirst(newPaging.getFirst());
		qvo.setLast(newPaging.getLast());
		List<QuestionVO> qList = classQMapper.getQuestionList(qvo);
		for(QuestionVO que : qList) {
			MediaVO mvo = new MediaVO();
			mvo.setGroups("회원");
			mvo.setGroupId(que.getWriterId());
			que.setProfile(mediaService.getMedia(mvo));
			que.setNickname(memberService.getMember(que.getWriterId()).getNickname());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("qList", qList);
		result.put("qPaging", newPaging);
		return result;
	}

	@Override
	public QuestionVO getQuestion(QuestionVO vo) {
		QuestionVO result = classQMapper.getQuestion(vo);
		MediaVO mvo = new MediaVO();
		mvo.setGroups("회원");
		mvo.setGroupId(result.getWriterId());
		result.setProfile(mediaService.getMedia(mvo));
		result.setNickname(memberService.getMember(result.getWriterId()).getNickname());
		return result;
	}

	@Override
	public int questionCount(QuestionVO vo) {
		
		return classQMapper.questionCount(vo).getTotalRecord();
	}

	@Override
	public QuestionVO insertQuestion(QuestionVO vo) {
		classQMapper.insertQuestion(vo);
		return vo;
	}

	@Override
	public boolean updateQuestion(QuestionVO vo) {
		if(classQMapper.updateQuestion(vo)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateAnswer(QuestionVO vo) {
		if(classQMapper.updateAnswer(vo)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Map<String, Object> getMyQuestionList(QuestionVO vo, Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}
}
