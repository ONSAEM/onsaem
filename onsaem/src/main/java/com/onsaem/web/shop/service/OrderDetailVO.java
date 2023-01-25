package com.onsaem.web.shop.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailVO {
	Integer orderId;	//주문코드
	Integer productId;	
	Integer detailAmount;
	String detailOption;
	String orderDetailId;
}
