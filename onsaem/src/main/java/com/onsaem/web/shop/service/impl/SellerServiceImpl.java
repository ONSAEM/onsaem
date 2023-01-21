package com.onsaem.web.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.shop.mapper.SellerMapper;
import com.onsaem.web.shop.service.ProductVO;
import com.onsaem.web.shop.service.SellerService;

@Component
public class SellerServiceImpl implements SellerService{
	@Autowired
	SellerMapper sellMapper;
	
	@Override
	public List<ProductVO> waitList(String data) {
		// 승인대기
		return sellMapper.waitList(data);
	}

	@Override
	public List<ProductVO> waitSell(String data) {
		// 판매대기
		return sellMapper.waitSell(data);
	}

	@Override
	public List<ProductVO> selling(String data) {
		// 판매중
		return sellMapper.selling(data);
	}

	@Override
	public List<ProductVO> endSell(String data) {
		// 품절
		return sellMapper.endSell(data);
	}

	@Override
	public List<ProductVO> stopSell(String data) {
		// 판매중지
		return sellMapper.stopSell(data);
	}

	@Override
	public List<ProductVO> banSell(String data) {
		// 판매정지
		return sellMapper.banSell(data);
	}

	@Override
	public List<ProductVO> countList(String data) {
		// 품목별갯수
		return sellMapper.countList(data);
	}

}
