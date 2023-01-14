package com.onsaem.web.blog.mapper;

import java.util.List;

import com.onsaem.web.blog.service.MomentsVO;

public interface MomentMapper {
	// 모먼트 작성
	
	// 모먼트 삭제
	
	// 한 블로거의 모먼트 전체 조회(4개만 출력)
	public List<MomentsVO> getMomentList(MomentsVO momentsVO);
	// 한 블로거의 모먼트 단건 조회
	public MomentsVO getMoment(String momentId);
	// 좋아요 
	
	
}
