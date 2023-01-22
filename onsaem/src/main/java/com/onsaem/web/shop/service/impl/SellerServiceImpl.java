package com.onsaem.web.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.shop.mapper.SellerMapper;
import com.onsaem.web.shop.service.ProductVO;
import com.onsaem.web.shop.service.SellerService;

@Component
public class SellerServiceImpl implements SellerService{
	@Autowired
	SellerMapper sellMapper;
	
	@Override
	public List<ProductVO> waitList(String data) {
		// 승인대기
		return sellMapper.waitList(data);
	}

	@Override
	public List<ProductVO> waitSell(String data) {
		// 판매대기
		return sellMapper.waitSell(data);
	}

	@Override
	public List<ProductVO> selling(String data) {
		// 판매중
		return sellMapper.selling(data);
	}

	@Override
	public List<ProductVO> endSell(String data) {
		// 품절
		return sellMapper.endSell(data);
	}

	@Override
	public List<ProductVO> stopSell(String data) {
		// 판매중지
		return sellMapper.stopSell(data);
	}

	@Override
	public List<ProductVO> banSell(String data) {
		// 판매정지
		return sellMapper.banSell(data);
	}

	@Override
	public List<ProductVO> countList(String data) {
		// 품목별갯수
		return sellMapper.countList(data);
	}

	@Override
	public List<ProductVO> arrayProductAll(ProductVO vo) {
		// 전체 목록조회
		return sellMapper.arrayProductAll(vo);
	}

	@Override
	public List<ProductVO> arrayProductName(ProductVO vo) {
		// 상품명 조회
		return sellMapper.arrayProductName(vo);
	}

	@Override
	public List<ProductVO> arrayProductId(ProductVO vo) {
		// 상품번호 조회
		return sellMapper.arrayProductId(vo);
	}

	@Override
	public List<ProductVO> statusProduct(String data) {
		// 판매상태별 조회
		return sellMapper.statusProduct(data);
	}

	@Override
	public List<ProductVO> amountProduct(String data) {
		// 재고별 조회
		return sellMapper.amountProduct(data);
	}

	@Override
	public List<ProductVO> categoryProduct(String data) {
		// 품목별 조회
		return sellMapper.categoryProduct(data);
	}

	@Override
	public List<ProductVO> dateProduct(ProductVO vo) {
		// 날짜별 조회
		return sellMapper.dateProduct(vo);
	}

	@Override
	public int endProduct(ProductVO vo) {
		// 품절처리
		return sellMapper.endProduct(vo);
	}

}
