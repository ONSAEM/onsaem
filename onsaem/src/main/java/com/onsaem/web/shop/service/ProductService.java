package com.onsaem.web.shop.service;

import java.util.List;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.ReportVO;
import com.onsaem.web.common.service.ReviewVO;
import com.onsaem.web.member.service.MemberVO;

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
	
	//신고처리
	int addBan(ReportVO vo);
	
	//친환경이미지
	List<ProductVO> natureImg(String data);
	
	//상품리뷰리스트
	List<ReviewVO> reviewList(String data);
	
	//나의찜삭제
	int delMyLike(ProductVO vo);

}
