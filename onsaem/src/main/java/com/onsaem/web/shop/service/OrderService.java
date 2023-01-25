package com.onsaem.web.shop.service;

import java.util.List;

import com.onsaem.web.common.service.PaymentVO;
import com.onsaem.web.member.service.MemberVO;

public interface OrderService {
	// 구매자정보가져오기
	List<MemberVO> userData(MemberVO vo);

	// 나의 주문목록가져오기
	List<OrderVO> myOrderList(OrderVO vo);
	
	// 주문하기(일반)
	int orderProduct(OrderVO vo);
	
	// 주문하기(상세)
	int orderDetail(OrderVO vo);
	
	//결제데이터입력
	int insertOrder(PaymentVO vo);
}