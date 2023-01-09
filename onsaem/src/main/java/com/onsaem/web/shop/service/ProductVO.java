package com.onsaem.web.shop.service;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVO {
	String productId;	//상품코드
	String productName;	//상품명
	String productDetail;	//상세설명
	int price;	//판매가
	double star;	//별점
	int amount;	//재고량
	int deliveryFee;	//배송비	
	String options;	//옵션
	String category;	//카테고리
	String natureId;	//친환경인증증서코드
	String productStatus;	//상품상태
	Date registerDate;	//등록일자
	String memberId;	//판매자ID
	
	//조인해서 가져올 변수
	String fileName;
	String fileRoute;
	String mediaName;
}
