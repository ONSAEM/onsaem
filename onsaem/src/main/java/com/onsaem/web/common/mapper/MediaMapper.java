package com.onsaem.web.common.mapper;

import com.onsaem.web.common.service.MediaVO;

public interface MediaMapper {
	
	// 미디어경로 DB에 저장
	public int insertMedia(MediaVO vo);
	
	// 미디어 조회
	public MediaVO getMedia();

	// 영상 조회
	public MediaVO getvideo();

	// 미디어 사용중지상태로 변경
	public int stopMedia(MediaVO vo);
}
