package com.onsaem.web.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.common.service.ReviewVO;
import com.onsaem.web.shop.mapper.SellerMapper;
import com.onsaem.web.shop.service.OrderVO;
import com.onsaem.web.shop.service.ProductVO;
import com.onsaem.web.shop.service.SellerService;

@Component
public class SellerServiceImpl implements SellerService {
	@Autowired
	SellerMapper sellMapper;

	@Override
	public List<ProductVO> waitList(String data) {
		// 승인대기
		return sellMapper.waitList(data);
	}

	@Override
	public List<ProductVO> waitSell(String data) {
		// 판매대기
		return sellMapper.waitSell(data);
	}

	@Override
	public List<ProductVO> selling(String data) {
		// 판매중
		return sellMapper.selling(data);
	}

	@Override
	public List<ProductVO> endSell(String data) {
		// 품절
		return sellMapper.endSell(data);
	}

	@Override
	public List<ProductVO> stopSell(String data) {
		// 판매중지
		return sellMapper.stopSell(data);
	}

	@Override
	public List<ProductVO> banSell(String data) {
		// 판매정지
		return sellMapper.banSell(data);
	}

	@Override
	public List<ProductVO> countList(String data) {
		// 품목별갯수
		return sellMapper.countList(data);
	}

	@Override
	public List<ProductVO> arrayProductAll(ProductVO vo) {
		// 전체 목록조회
		return sellMapper.arrayProductAll(vo);
	}

	@Override
	public List<ProductVO> arrayProductName(ProductVO vo) {
		// 상품명 조회
		return sellMapper.arrayProductName(vo);
	}

	@Override
	public List<ProductVO> arrayProductId(ProductVO vo) {
		// 상품번호 조회
		return sellMapper.arrayProductId(vo);
	}

	@Override
	public List<ProductVO> statusProduct(String data) {
		// 판매상태별 조회
		return sellMapper.statusProduct(data);
	}

	@Override
	public List<ProductVO> amountProduct(String data) {
		// 재고별 조회
		return sellMapper.amountProduct(data);
	}

	@Override
	public List<ProductVO> categoryProduct(String data) {
		// 품목별 조회
		return sellMapper.categoryProduct(data);
	}

	@Override
	public List<ProductVO> dateProduct(ProductVO vo,String startDate,String endDate) {
		// 날짜별 조회
		return sellMapper.dateProduct(vo,startDate,endDate);
	}

	@Override
	public int endProduct(ProductVO vo) {
		// 품절처리
		return sellMapper.endProduct(vo);
	}

	@Override
	public List<OrderVO> orderList(String data) {
		// 판매자 주문목록
		return sellMapper.orderList(data);
	}

	@Override
	public List<OrderVO> changeDate(String startDate, String endDate, String id) {
		// 주문목록 날짜조회
		return sellMapper.changeDate(startDate, endDate, id);
	}

	@Override
	public List<OrderVO> todaySearch(String id) {
		// 오늘주문조회
		return sellMapper.todaySearch(id);
	}

	@Override
	public List<OrderVO> weekSearch(String id) {
		// 이번주조회
		return sellMapper.weekSearch(id);
	}

	@Override
	public List<OrderVO> monthSearch(String id) {
		// 한달조회
		return sellMapper.monthSearch(id);
	}

	@Override
	public List<OrderVO> arrayOrderAll(String id, String data) {
		// 전체 주문조회
		return sellMapper.arrayOrderAll(id, data);
	}

	@Override
	public List<OrderVO> buyName(String id, String data) {
		// 구매자명조회
		return sellMapper.buyName(id, data);
	}

	@Override
	public List<OrderVO> buyId(String id, String data) {
		// 구매자ID조회
		return sellMapper.buyId(id, data);
	}

	@Override
	public List<OrderVO> orderNumber(String id, String data) {
		// 주문번호 조회
		return sellMapper.orderNumber(id, data);	
	}
	
	@Override
	public List<OrderVO> productId(String id, String data) {
		// 상품번호 조회
		return sellMapper.productId(id, data);	
	}

	@Override
	public List<OrderVO> orderExchange(String id) {
		// 교환 반품목록 조회
		return sellMapper.orderExchange(id);	
	}
	
	@Override
	public List<OrderVO> endShipping(String id) {
		// 배송완료 조회
		return sellMapper.endShipping(id);	
	}
	
	@Override
	public List<OrderVO> shipping(String id) {
		// 배송중 조회
		return sellMapper.shipping(id);	
	}
	
	@Override
	public List<OrderVO> waitOrder(String id) {
		// 주문대기 조회
		return sellMapper.waitOrder(id);	
	}

	@Override
	public int sumbitShipping(OrderVO vo) {
		// 주문상태 변경
		return sellMapper.sumbitShipping(vo);
	}

	@Override
	public List<ReviewVO> sellerReview(String data) {
		// 판매자 리뷰리스트 가져오기
		return sellMapper.sellerReview(data);		
	}

	@Override
	public int sellerReviewContent(ReviewVO vo) {
		// 판매자 리뷰댓글달기
		return sellMapper.sellerReviewContent(vo);
	}

}
