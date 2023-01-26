package com.onsaem.web.shop.mapper;

import java.util.List;

import com.onsaem.web.common.service.ReviewVO;
import com.onsaem.web.shop.service.OrderVO;
import com.onsaem.web.shop.service.ProductVO;

public interface SellerMapper {
	// 판매자 리뷰댓글 달기
	int sellerReviewContent(ReviewVO vo);

	// 판매자 리뷰리스트 가져오기
	List<ReviewVO> sellerReview(String data);

	// 주문상태 변경
	int sumbitShipping(OrderVO vo);

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

	// 판매자 주문목록
	List<OrderVO> orderList(String data);

	// 주문목록 날짜조회
	List<OrderVO> changeDate(String startDate, String endDate, String id);

	// 오늘 주문조회
	List<OrderVO> todaySearch(String id);

	// 이번주 주문조회
	List<OrderVO> weekSearch(String id);

	// 한달 조회
	List<OrderVO> monthSearch(String id);

	// 전체 주문 조회
	List<OrderVO> arrayOrderAll(String id, String data);

	// 구매자명 조회
	List<OrderVO> buyName(String id, String data);

	// 구매자Id 조회
	List<OrderVO> buyId(String id, String data);

	// 주문번호 조회
	List<OrderVO> orderNumber(String id, String data);

	// 상품번호 조회
	List<OrderVO> productId(String id, String data);

	// 교환/반품목록 조회
	List<OrderVO> orderExchange(String id);

	// 배송완료 조회
	List<OrderVO> endShipping(String id);

	// 배송중 조회
	List<OrderVO> shipping(String id);

	// 주문대기 조회
	List<OrderVO> waitOrder(String id);

}
