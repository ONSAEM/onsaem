package com.onsaem.web.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.shop.mapper.CartMapper;
import com.onsaem.web.shop.service.CartService;
import com.onsaem.web.shop.service.CartVO;

@Component
public class CartServiceImpl implements CartService{

	@Autowired CartMapper cartMapper;
	
	@Override
	public int cartAdd(CartVO vo) {
		// 장바구니 답기
		return cartMapper.cartAdd(vo);
	}

	@Override
	public List<CartVO> cartList(CartVO vo) {
		// 장바구니 리스트
		return cartMapper.cartList(vo);
	}

}
