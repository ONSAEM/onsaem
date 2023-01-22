package com.onsaem.web.member.mapper;

import com.onsaem.web.member.service.MemberVO;

public interface MemberMapper {

	// 로그인(유저조회)
	public MemberVO getMember(String memberId);

	// 아이디 체크
	public MemberVO checkId(String memberId);

	// 이메일로 회원조회
	public int searchEmail(String email);

	// 이메일에 보낼 아이디
	public MemberVO idEmail(String email);

	// 회원등록
	public int insertMember(MemberVO member);

	// 비밀번호 변경
	public int updatePw(MemberVO member);

	// 회원정보변경
	public int updateMember(MemberVO member);
}
