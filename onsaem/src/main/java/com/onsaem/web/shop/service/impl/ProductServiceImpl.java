package com.onsaem.web.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.shop.mapper.ProductMapper;
import com.onsaem.web.shop.service.ProductService;
import com.onsaem.web.shop.service.ProductVO;

@Component
public class ProductServiceImpl implements ProductService{
	@Autowired ProductMapper proMapper;

	@Override
	public List<ProductVO> proList() {
		// 상품전체조회(최신순)		
		return proMapper.proList();
	}

	@Override
	public List<ProductVO> popList() {
		// 상품전체조회(인기순)
		
		return proMapper.popList();
	}

	@Override
	public List<ProductVO> proCategory(String data) {
		// 카테고리별상품조회		
		return proMapper.proCategory(data);
	}

	@Override
	public int likeAdd(LikeVO vo) {
		// 찜담기
		return proMapper.likeAdd(vo);
	}
	
	@Override
	public int likeDel(LikeVO vo) {
		// 찜 삭제
		return proMapper.likeDel(vo);
	}

	@Override
	public List<ProductVO> likeList(LikeVO vo) {
		// 찜정보가져오기
		return proMapper.likeList(vo);
	}

	@Override
	public List<ProductVO> selectPro(String data) {
		// 상품 상세보기
		return proMapper.selectPro(data);
	}

	@Override
	public List<ProductVO> addImg(String data) {
		// 추가이미지
		return proMapper.addImg(data);
	}

	@Override
	public List<ProductVO> searchProduct(String data) {
		// 검색목록
		return proMapper.searchProduct(data);
	}

	@Override
	public List<ProductVO> categoryList() {
		// 카테고리 리스트
		return proMapper.categoryList();
	}

	@Override
	public int addProduct(ProductVO vo) {
		// 상품등록 신청
		return proMapper.addProduct(vo);
	}

	@Override
	public int addMedia(MediaVO vo) {
		// 상품이미지등록
		return proMapper.addMedia(vo);
	}

	
	
}
