package com.onsaem.web.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.member.service.MemberVO;
import com.onsaem.web.shop.mapper.OrderMapper;
import com.onsaem.web.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired OrderMapper ordMapper;
	
	@Override
	public List<MemberVO> userData(MemberVO vo) {
		//구매자정보가져오기
		return ordMapper.userData(vo);
	}

}