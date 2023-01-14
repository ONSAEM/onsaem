package com.onsaem.web.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.blog.mapper.MomentMapper;
import com.onsaem.web.blog.service.MomentService;
import com.onsaem.web.blog.service.MomentsVO;

@Service
public class MomentServiceImpl implements MomentService {
	
	@Autowired MomentMapper momentMapper;
	@Override
	public List<MomentsVO> getMomentList(MomentsVO momentsVO) {
		// 한 블로거의 모먼트 전체 조회
		return momentMapper.getMomentList(momentsVO);
	}

	@Override
	public MomentsVO getMoment(String momentId) {
		// 한 블로거의 모먼트 단건 조회
		return momentMapper.getMoment(momentId);
	}

}
