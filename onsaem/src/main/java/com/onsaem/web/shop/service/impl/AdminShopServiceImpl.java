package com.onsaem.web.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.shop.mapper.AdminShopMapper;
import com.onsaem.web.shop.service.AdminShopService;
import com.onsaem.web.shop.service.ProductVO;

@Service
public class AdminShopServiceImpl implements AdminShopService{
	@Autowired AdminShopMapper mapper;

	@Override
	public int adminApproProduct(ProductVO vo) {
		// 승인처리하기
		return mapper.adminApproProduct(vo);
	}


	
}
