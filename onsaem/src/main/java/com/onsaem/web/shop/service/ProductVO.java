package com.onsaem.web.shop.service;

import java.sql.Date;

import lombok.Data;

@Data
public class ProductVO {
	String productId;
	String productName;
	String productDetail;
	int price;
	int star;
	int amount;
	int deliveryFee;
	String options;
	String category;
	String natureId;
	String productStatus;
	Date registerDate;
	String memberId;
}
