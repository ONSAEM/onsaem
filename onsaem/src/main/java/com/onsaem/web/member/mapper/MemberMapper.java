package com.onsaem.web.member.mapper;


import com.onsaem.web.member.service.MemberVO;

public interface MemberMapper {

	// 로그인(유저조회)
	public MemberVO getMember(String memberId);

	// 아이디 체크
	public MemberVO checkId(String memberId);
	
	// 회원등록
	public int insertMember(MemberVO member);
}
