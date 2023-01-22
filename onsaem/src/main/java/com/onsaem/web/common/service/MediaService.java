package com.onsaem.web.common.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
	
	// 미디어파일 등록
	public List<MediaVO> uploadMedia(MultipartFile[] uploadfile,MediaVO vo) throws IllegalStateException, IOException;
	
	// 미디어 사용중지상태로 변경
	public int stopMedia(MediaVO vo);
	
}
