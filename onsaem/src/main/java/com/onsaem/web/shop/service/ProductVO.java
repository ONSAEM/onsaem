package com.onsaem.web.shop.service;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {
	String productId;	//상품코드
	String productName;	//상품명
	String productDetail;	//상세설명
	int price;	//판매가
	double star;	//별점
	int amount;	//재고량
	int deliveryFee;	//배송비	
	String optionId;	//옵션
	String category;	//카테고리
	String natureId;	//친환경인증증서코드
	String productStatus;	//상품상태
	Date registerDate;	//등록일자
	String memberId;	//판매자ID
	String productInfo; //상품 상세설명
	String sellerAddr; //판매자반품주소
	
	//이미지
	String fileName;
	String fileRoute;
	String mediaName;
	
	//찜
	String groupId;
	String groups;
	Date insertDate;
	String likeId;
}
