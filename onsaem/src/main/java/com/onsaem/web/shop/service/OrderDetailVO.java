package com.onsaem.web.shop.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailVO {
	int orderId;	//주문코드
	int productId;	
	int detailAmount;
	String detailOption;
	String orderDetailId;
}
