package com.onsaem.web.shop.service;

import java.util.List;

import com.onsaem.web.member.service.MemberVO;

public interface OrderService {
	//구매자정보가져오기
		List<MemberVO> userData(MemberVO vo);
}