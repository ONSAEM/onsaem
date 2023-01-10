package com.onsaem.web.shop.mapper;

import java.util.List;

import com.onsaem.web.shop.service.CartVO;

public interface CartMapper {
	// 장바구니 담기
	int cartAdd(CartVO vo);

	// 장바구니 리스트
	List<CartVO> cartList();
}
