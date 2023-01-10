package com.onsaem.web.member.mapper;


import com.onsaem.web.member.service.MemberVO;

public interface LoginMapper {
	// 로그인
	MemberVO memberLogin(MemberVO vo);
}
