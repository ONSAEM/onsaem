package com.onsaem.web.shop.mapper;

import java.util.List;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.ReportVO;
import com.onsaem.web.common.service.ReviewVO;
import com.onsaem.web.shop.service.OptionVO;
import com.onsaem.web.shop.service.OrderVO;
import com.onsaem.web.shop.service.ProductVO;

public interface ProductMapper {
	// 리뷰갯수
	List<ReviewVO> totalReview(ReviewVO vo);

	// 리뷰작성가능여부
	List<OrderVO> compareReview(OrderVO vo);

	// 승인대기품목
	List<ProductVO> approProduct();

	// 리뷰 별점 주기
	int updateStar(ReviewVO vo);

	// 나의 리뷰리스트
	List<ReviewVO> myReviewList(String data);

	// 리뷰 포인트 적립
	int reviewPoint(String data);

	// 주간 베스트 데이터 가져오기
	List<ProductVO> selectProduct(List<String> list);

	// 주간 베스트
	List<ProductVO> weekBest();

	// 상품전체조회
	List<ProductVO> proList(ProductVO vo);

	// 상품 갯수 조회
	int proCount(ProductVO vo);

	// 옵션 갯수 조회
	int cateCount(String data);

	// 인기상품순 갯수 조회
	int popCount(ProductVO vo);

	// 상품전체목록(인기순)
	List<ProductVO> popList(ProductVO vo);

	// 카테고리별 상품목록
	List<ProductVO> proCategory(String data, ProductVO vo);

	// 검색목록
	List<ProductVO> searchProduct(String data, ProductVO vo);

	// 검색목록 전체 갯수 조회
	int searchCount(String data);

	// 찜 담기
	int likeAdd(LikeVO vo);

	// 찜 삭제
	int likeDel(LikeVO vo);

	// 찜 리스트
	List<ProductVO> likeList(LikeVO vo);

	// 상품 상세보기
	List<ProductVO> selectPro(String data);

	// 추가이미지
	List<ProductVO> addImg(String data);

	// 카테고리 리스트
	List<ProductVO> categoryList();

	// 상품등록 신청
	int addProduct(ProductVO vo);

	// 상품이미지 등록
	int addMedia(MediaVO vo);

	// 신고등록
	int addBan(ReportVO vo);

	// 친환경이미지
	List<ProductVO> natureImg(String data);

	// 상품리뷰리스트
	List<ReviewVO> reviewList(String data);

	// 나의찜삭제
	int delMyLike(ProductVO vo);

	// 옵션들 저장
	int addOption(OptionVO ovo);

	// 옵션들 가져오기
	List<ProductVO> optionList(String data);

	// 리뷰작성
	int addReview(ReviewVO vo);

	// 판매자 상품 가져오기
	List<ProductVO> sellerList(ProductVO vo);

	// 최근 본 목록 가져오기
	List<ProductVO> watchProduct(ProductVO vo);

}
