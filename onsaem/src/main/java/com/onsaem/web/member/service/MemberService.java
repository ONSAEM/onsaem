package com.onsaem.web.member.service;

import java.util.List;

import com.onsaem.web.common.service.MediaVO;

public interface MemberService {
	public MemberVO getMember(String memberId);
	public String sendAuthMail(String email,List<MediaVO> fileList);
}
