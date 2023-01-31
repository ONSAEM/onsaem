package com.onsaem.web.member.mapper;

import com.onsaem.web.member.service.ApplyMemberVO;

public interface ApplyMapper {

	// 신청등록
	public int insertApply(ApplyMemberVO vo);
}
