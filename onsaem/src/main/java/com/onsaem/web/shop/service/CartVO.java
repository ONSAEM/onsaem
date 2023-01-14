package com.onsaem.web.shop.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartVO {
	String cartId;
	String productId;
	int cartAmount;
	String cartOption;
	int cartPrice;
	String memberId;
	
	String productName;
	int deliveryFee;
	String fileRoute;
	String mediaName;
	
}
