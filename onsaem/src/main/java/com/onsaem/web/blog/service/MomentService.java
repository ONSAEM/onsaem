package com.onsaem.web.blog.service;

import java.util.List;

import com.onsaem.web.common.service.MediaVO;

public interface MomentService {
	// 한 블로거의 모먼트 전체 조회(4개만 출력)
	public List<MomentsVO> getMomentList(MomentsVO momentsVO);
	// 한 블로거의 모먼트 단건 조회
	public MomentsVO getMoment(String momentId);
	// 모먼트 등록
	int momentInsert(MomentsVO momentsVO);
	// 모먼트 사진 등록
	int momentMediaInsert(MediaVO mediaVo);
	// 모먼트 삭제
	int momentDel(String momentId);
}
