package com.onsaem.web.shop.service;

import java.util.List;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.ReportVO;
import com.onsaem.web.common.service.ReviewVO;

public interface ProductService {
	//리뷰갯수
	List<ReviewVO> totalReview(ReviewVO vo);
	
	//리뷰작성여부판단
	List<OrderVO> compareReview(OrderVO vo);
	
	//승인대기품목
	List<ProductVO> approProduct();
	
	//리뷰 별점 주기
	int updateStar(ReviewVO vo);
	
	//나의 리뷰리스트
	List<ReviewVO> myReviewList(String data);
	
	//리뷰 포인트 적립
	int reviewPoint(String data);
	
	//주간 베스트 데이터 가져오기
	List<ProductVO> selectProduct(List<String> list);
	
	//주간 베스트
	List<ProductVO> weekBest();	
	
	//상품전체목록(최신순)
	List<ProductVO> proList(ProductVO vo, Paging paging);
	
	//상품전체목록(인기순)
	List<ProductVO> popList(ProductVO vo, Paging paging);
	
	//카테고리별상품목록
	List<ProductVO> proCategory(String data,Paging paging,ProductVO vo);
	
	//검색목록
	List<ProductVO> searchProduct(String data,Paging paging,ProductVO vo);
	
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
	
	//옵션들 저장
	int addOption(OptionVO ovo);
	
	//옵션들 가져오기
	List<ProductVO> optionList(String data);
	
	//리뷰작성
	int addReview(ReviewVO vo);
	
	//판매자 상품 가져오기
	List<ProductVO> sellerList(ProductVO vo);
	
	//최근 목록 가져오기
	List<ProductVO> watchProduct(ProductVO vo);
}
