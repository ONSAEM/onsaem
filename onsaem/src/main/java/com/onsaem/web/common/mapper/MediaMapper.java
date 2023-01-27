package com.onsaem.web.common.mapper;

import java.util.List;

import com.onsaem.web.common.service.MediaVO;

public interface MediaMapper {

	// 미디어경로 DB에 저장
	public int insertMedia(MediaVO vo);

	// 미디어 조회
	public MediaVO getMedia(MediaVO vo);

	// 미디어 목록 조회
	public List<MediaVO> getMediaList(MediaVO vo);

	// 미디어 사용중지상태로 변경
	public int stopMedia(MediaVO vo);
}
