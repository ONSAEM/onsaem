package com.onsaem.web.member.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ApplyService {

	// 신청 등록
	public String insertApply(MultipartFile[] applyFile, ApplyMemberVO vo) throws IllegalStateException, IOException;
}
