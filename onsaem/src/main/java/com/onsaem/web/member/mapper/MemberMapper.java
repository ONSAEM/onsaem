package com.onsaem.web.member.mapper;


import com.onsaem.web.member.service.MemberVO;

public interface MemberMapper {
	
	// 로그인(유저조회)
	MemberVO getMember(String memberId);
	
}
