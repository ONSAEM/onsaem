package com.onsaem.web.member.service;

public interface MemberService {
	public MemberVO getMember(String memberId);
	
	// 이메일 인증
	public String sendAuthMail(String email);
}
