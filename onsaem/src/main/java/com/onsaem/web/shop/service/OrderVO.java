package com.onsaem.web.shop.service;

import java.sql.Date;

import lombok.Data;

@Data
public class OrderVO {
	String orderId;
	Date orderDate;
	String orderStatus;
	String memberId;
	String recivName;
	String recivAddr;
	int recivPhone;
	String recivReq;
	int deliveryFee;
	String deliveryId;
}
