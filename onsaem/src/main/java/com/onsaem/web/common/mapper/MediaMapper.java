package com.onsaem.web.common.mapper;

import com.onsaem.web.common.service.MediaVO;

public interface MediaMapper {
	public int insertMedia(MediaVO vo);
	public MediaVO getMedia();
	public MediaVO getvideo();
}
