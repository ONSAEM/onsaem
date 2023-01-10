package com.onsaem.web.shop.mapper;

import java.util.List;

import com.onsaem.web.shop.service.ProductVO;

public interface ProductMapper {
	// 상품전체조회
	List<ProductVO> proList();

	// 상품전체목록(인기순)
	List<ProductVO> popList();

	// 카테고리별 상품목록
	List<ProductVO> proCategory(String data);
	
	//검색목록
		List<ProductVO> searchProduct(String data);

	// 찜 담기
	int likeAdd(String data);

	// 찜 삭제
	int likeDel(String data);

	// 찜 리스트
	List<ProductVO> likeList();

	// 상품 상세보기
	List<ProductVO> selectPro(String data);

	// 추가이미지
	List<ProductVO> addImg(String data);
	
	

}
