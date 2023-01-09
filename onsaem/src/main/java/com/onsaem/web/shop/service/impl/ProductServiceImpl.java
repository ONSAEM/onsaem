package com.onsaem.web.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.shop.mapper.ProductMapper;
import com.onsaem.web.shop.service.ProductService;
import com.onsaem.web.shop.service.ProductVO;

@Component
public class ProductServiceImpl implements ProductService{
	@Autowired ProductMapper proMapper;

	@Override
	public List<ProductVO> proList() {
		// 상품전체조회(최신순)		
		return proMapper.proList();
	}

	@Override
	public List<ProductVO> popList() {
		// 상품전체조회(인기순)		
		return proMapper.popList();
	}

	@Override
	public List<ProductVO> proCategory(String data) {
		// 카테고리별상품조회		
		return proMapper.proCategory(data);
	}

	@Override
	public List<ProductVO> searchProduct(String data) {
		// 검색별 상품목록조회
		return proMapper.searchProduct(data);
	}
	
}
