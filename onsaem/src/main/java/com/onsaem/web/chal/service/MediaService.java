package com.onsaem.web.chal.service;

public interface MediaService {
		//사진등록
		Integer inputMedia(MediaVO vo);
		
		//사진 안보이게 하는거도 포함
		Integer updateMedia(MediaVO vo);
}
