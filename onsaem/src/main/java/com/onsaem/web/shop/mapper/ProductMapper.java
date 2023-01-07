package com.onsaem.web.shop.mapper;

import java.util.List;

import com.onsaem.web.shop.service.ProductVO;

public interface ProductMapper {
	//상품전체조회
	List<ProductVO> proList();
}
