package com.onsaem.web.shop.mapper;

import java.util.List;

import com.onsaem.web.shop.service.ProductVO;

public interface SellerMapper {
	// 승인대기품목
	List<ProductVO> waitList(String data);

	// 판매대기품목
	List<ProductVO> waitSell(String data);

	// 판매중
	List<ProductVO> selling(String data);

	// 품절
	List<ProductVO> endSell(String data);

	// 판매중지
	List<ProductVO> stopSell(String data);

	// 판매정지
	List<ProductVO> banSell(String data);
	
	// 상태별 갯수
		List<ProductVO> countList(String data);
}
