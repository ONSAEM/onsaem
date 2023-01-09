package com.onsaem.web.shop.mapper;

import java.util.List;

import com.onsaem.web.shop.service.ProductVO;

public interface ProductMapper {
	//상품전체조회
	List<ProductVO> proList();
	
	//상품전체목록(인기순)
	List<ProductVO> popList();
		
	//카테고리별 상품목록
	List<ProductVO> proCategory(String data);
	
}
