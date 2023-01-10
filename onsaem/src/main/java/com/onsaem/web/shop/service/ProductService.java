package com.onsaem.web.shop.service;

import java.util.List;

public interface ProductService {
	//상품전체목록(최신순)
	List<ProductVO> proList();
	
	//상품전체목록(인기순)
	List<ProductVO> popList();
	
	//카테고리별상품목록
	List<ProductVO> proCategory(String data);
	
	//검색목록
	List<ProductVO> searchProduct(String data);
	
	//찜 담기
	int likeAdd(String data);
	
	//찜 삭제
	int likeDel(String data);
	
	//찜 리스트
	List<ProductVO> likeList();
	
	//상품 상세보기
	List<ProductVO> selectPro(String data);
	
	//추가이미지
	List<ProductVO> addImg(String data);
		
}
