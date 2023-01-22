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

	// 전체조회
	List<ProductVO> arrayProductAll(ProductVO vo);

	// 상품명조회
	List<ProductVO> arrayProductName(ProductVO vo);

	// 상품번호조회
	List<ProductVO> arrayProductId(ProductVO vo);

	// 상태별 조회
	List<ProductVO> statusProduct(String data);

	// 수량별 조회
	List<ProductVO> amountProduct(String data);

	// 품목별 조회
	List<ProductVO> categoryProduct(String data);

	// 날짜별 조회
	List<ProductVO> dateProduct(ProductVO vo);

	// 품절처리
	int endProduct(ProductVO vo);

}
