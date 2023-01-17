package com.onsaem.web.shop.mapper;

import java.util.List;

import com.onsaem.web.member.service.MemberVO;
import com.onsaem.web.shop.service.OrderVO;

public interface OrderMapper {
	// 구매자정보가져오기
	List<MemberVO> userData(MemberVO vo);

	//나의 주문목록 가져오기
	List<OrderVO> myOrderList(OrderVO vo);
}