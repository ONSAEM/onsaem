package com.onsaem.web.member.service;

public interface MemberService {

	// 회원단건조회
	public MemberVO getMember(String memberId);

	// 아이디 체크
	public MemberVO checkId(String memberId);

	// 이메일 인증
	public String sendAuthMail(String email);
}
