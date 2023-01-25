package com.onsaem.web.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.common.service.PaymentVO;
import com.onsaem.web.member.service.MemberVO;
import com.onsaem.web.shop.mapper.OrderMapper;
import com.onsaem.web.shop.service.OrderService;
import com.onsaem.web.shop.service.OrderVO;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired OrderMapper ordMapper;
	
	@Override
	public List<MemberVO> userData(MemberVO vo) {
		//구매자정보가져오기
		return ordMapper.userData(vo);
	}

	@Override
	public List<OrderVO> myOrderList(OrderVO vo) {
		// 나의 주문목록 가져오기
		return ordMapper.myOrderList(vo);
	}

	@Override
	public int orderProduct(OrderVO vo) {
		// 주문하기(일반)
		return ordMapper.orderProduct(vo);
	}

	@Override
	public int orderDetail(OrderVO vo) {
		// 주문하기(상세)
		return ordMapper.orderDetail(vo);
	}

	@Override
	public int insertOrder(PaymentVO vo) {
		// 결제 데이터 입력
		return ordMapper.insertOrder(vo);
	}

}