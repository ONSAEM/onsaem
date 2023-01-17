package com.onsaem.web.shop.service;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderVO {
	String orderId;	//주문코드
	Date orderDate;	//주문일자
	String orderStatus;	//주문상태
	String memberId;	//구매자ID
	String recivName;	//받는사람이름
	String recivAddr;	//받는사람주소
	int recivPhone;	//받는사람연락처
	String recivReq;	//요청사항
	int deliveryFee;	//배송비
	String deliveryId;	//배송코드
	
	String fileRoute;
	
	int detailAmount;
	String detailOption;
	String productName;
	int price;
}
