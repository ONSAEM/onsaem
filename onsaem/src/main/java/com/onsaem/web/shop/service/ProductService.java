package com.onsaem.web.shop.service;

import java.util.List;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaVO;

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
	int likeAdd(LikeVO vo);
	
	//찜 삭제
	int likeDel(LikeVO vo);
	
	//찜 리스트
	List<ProductVO> likeList(LikeVO vo);
	
	//상품 상세보기
	List<ProductVO> selectPro(String data);
	
	//추가이미지
	List<ProductVO> addImg(String data);
	
	//카테고리리스트
	List<ProductVO> categoryList();
	
	//상품등록신청
	int addProduct(ProductVO vo);
	
	//상품이미지등록
	int addMedia(MediaVO vo);
}
