package com.onsaem.web.shop.service;

import java.sql.Date;

import lombok.Data;

@Data
public class ExchangeVO {
	String exchangeId;
	String productId;
	String orderId;
	String cancelOption;
	String collAddr;
	String collRequest;
	Date collDate;
}
