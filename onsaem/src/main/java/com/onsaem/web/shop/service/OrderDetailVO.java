package com.onsaem.web.shop.service;

import lombok.Data;

@Data
public class OrderDetailVO {
	int orderId;
	int productId;
	int detailAmount;
	String detailOption;
	String orderDetailId;
}
