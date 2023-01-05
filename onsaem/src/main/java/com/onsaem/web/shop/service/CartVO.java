package com.onsaem.web.shop.service;

import lombok.Data;

@Data
public class CartVO {
	String cartId;
	String productId;
	int cartAmount;
	String cartOption;
	int cartPrice;
	String memberId;
}
