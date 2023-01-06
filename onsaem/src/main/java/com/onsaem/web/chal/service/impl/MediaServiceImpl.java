package com.onsaem.web.chal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.chal.mapper.MediaMapper;
import com.onsaem.web.chal.service.MediaService;
import com.onsaem.web.chal.service.MediaVO;

@Component
public class MediaServiceImpl implements MediaService {
	@Autowired MediaMapper mediaMapper;

	@Override
	public Integer inputMedia(MediaVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateMedia(MediaVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
