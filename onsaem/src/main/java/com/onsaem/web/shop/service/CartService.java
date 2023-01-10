package com.onsaem.web.shop.service;

import java.util.List;

public interface CartService {
	//장바구니 담기
	int cartAdd(String data);
	
	//장바구니 리스트
	List<CartVO> cartList();
}
