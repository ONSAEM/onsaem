package com.onsaem.web.common.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
	public List<MediaVO> uploadMedia(MultipartFile[] uploadfile,String groupId, String groups) throws IllegalStateException, IOException;
}
