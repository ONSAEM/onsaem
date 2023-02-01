package com.onsaem.web.member.mapper;

import java.util.List;

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
	
	// 블로그등록
	public int inserBlog(MemberVO member);

	// 비밀번호 변경
	public int updatePw(MemberVO member);

	// 회원정보변경
	public int updateMember(MemberVO member);
	
	// 회원 탈퇴
	public int deleteMember(MemberVO member);
	
	// 회원정보리스트
	public List<MemberVO> memberList();
	
	// 회원리스트(필터링)
	public List<MemberVO> searchList(MemberVO vo,String startDate, String endDate);
}
