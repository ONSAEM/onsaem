package com.onsaem.web.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.blog.mapper.MomentMapper;
import com.onsaem.web.blog.service.MomentService;
import com.onsaem.web.blog.service.MomentsVO;
import com.onsaem.web.common.service.MediaVO;

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

	@Override
	public int momentInsert(MomentsVO momentsVO) {
		// 모먼트 등록
		return momentMapper.momentInsert(momentsVO);
	}

	@Override
	public int momentMediaInsert(MediaVO mediaVo) {
		// 모먼트 사진 등록
		return momentMapper.momentMediaInsert(mediaVo);
	}

	@Override
	public int momentDel(String momentId) {
		// 모먼트 삭제
		return momentMapper.momentDel(momentId);
	}

	@Override
	public int momentCnt(String blogId) {
		// 모먼트 수 조회
		return momentMapper.momentCnt(blogId);
	}

}
