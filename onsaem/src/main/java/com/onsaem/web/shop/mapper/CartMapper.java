package com.onsaem.web.shop.mapper;

import java.util.List;

import com.onsaem.web.shop.service.CartVO;

public interface CartMapper {
	// 장바구니 담기
	int cartAdd(CartVO vo);

	// 장바구니 리스트
	List<CartVO> cartList(CartVO vo);
	
	// 나의장바구니 리스트
	List<CartVO> myCartList(CartVO vo);
	
	//장바구니 삭제
		int delCart(CartVO vo);
}
