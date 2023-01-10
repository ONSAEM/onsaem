package com.onsaem.web.shop.service;

import java.util.List;

public interface CartService {
	//장바구니 담기
	int cartAdd(CartVO vo);
	
	//장바구니 리스트
	List<CartVO> cartList();
}
