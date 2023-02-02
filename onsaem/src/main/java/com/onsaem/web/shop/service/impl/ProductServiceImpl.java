package com.onsaem.web.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.ReportVO;
import com.onsaem.web.common.service.ReviewVO;
import com.onsaem.web.shop.mapper.ProductMapper;
import com.onsaem.web.shop.service.OptionVO;
import com.onsaem.web.shop.service.OrderVO;
import com.onsaem.web.shop.service.ProductService;
import com.onsaem.web.shop.service.ProductVO;

@Component
public class ProductServiceImpl implements ProductService{
	@Autowired ProductMapper proMapper;

	@Override
	public List<ProductVO> proList(ProductVO vo,Paging paging) {
		// 상품전체조회(최신순)	
		paging.setTotalRecord(proMapper.proCount(vo));
		paging.setPageUnit(9);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return proMapper.proList(vo);
	}

	@Override
	public List<ProductVO> popList(ProductVO vo,Paging paging) {
		// 상품전체조회(인기순)
		paging.setTotalRecord(proMapper.popCount(vo));
		paging.setPageUnit(9);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return proMapper.popList(vo);
	}

	@Override
	public List<ProductVO> proCategory(String data,Paging paging,ProductVO vo) {
		// 카테고리별상품조회	
		paging.setTotalRecord(proMapper.cateCount(data));
		paging.setPageUnit(9);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return proMapper.proCategory(data,vo);
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
	public List<ProductVO> searchProduct(String data,Paging paging,ProductVO vo) {
		// 검색목록
		paging.setTotalRecord(proMapper.searchCount(data));
		paging.setPageUnit(9);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return proMapper.searchProduct(data,vo);
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

	@Override
	public int addBan(ReportVO vo) {
		// 신고등록
		return proMapper.addBan(vo);
	}

	@Override
	public List<ProductVO> natureImg(String data) {
		// 친환경이미지
		return proMapper.natureImg(data);
	}

	@Override
	public List<ReviewVO> reviewList(String data) {
		// 상품리뷰리스트
		return proMapper.reviewList(data);
	}

	@Override
	public int delMyLike(ProductVO vo) {
		// 나의찜삭제
		return proMapper.delMyLike(vo);
	}

	@Override
	public int addOption(OptionVO ovo) {
		// 옵션들 저장
		return proMapper.addOption(ovo);
	}

	@Override
	public List<ProductVO> optionList(String data) {
		// 옵션들 가져오기
		return proMapper.optionList(data);
	}

	@Override
	public int addReview(ReviewVO vo) {
		// 리뷰작성
		return proMapper.addReview(vo);
	}

	@Override
	public List<ProductVO> sellerList(ProductVO vo) {
		// 판매자 상품 리스트
		return proMapper.sellerList(vo);
	}

	@Override
	public List<ProductVO> watchProduct(ProductVO vo) {
		// 최근 본 목록 가져오기
		return proMapper.watchProduct(vo);
	}

	@Override
	public List<ProductVO> weekBest() {
		// 주간베스트
		return proMapper.weekBest();
	}

	@Override
	public List<ProductVO> selectProduct(List<String> list) {
		// 주간베스트 데이터 가져오기
		return proMapper.selectProduct(list);
	}

	@Override
	public int reviewPoint(String data) {
		// 리뷰 포인트 적립
		return proMapper.reviewPoint(data);
	}

	@Override
	public List<ReviewVO> myReviewList(String data) {
		// 나의 리뷰리스트
		return proMapper.myReviewList(data);
	}

	@Override
	public int updateStar(ReviewVO vo) {
		// 리뷰 별점 업데이트
		return proMapper.updateStar(vo);
	}

	@Override
	public List<ProductVO> approProduct() {
		// 승인대기품목
		return proMapper.approProduct();
	}

	@Override
	public List<OrderVO> compareReview(OrderVO vo) {
		// 리뷰 작성가능여부
		return proMapper.compareReview(vo);
	}

	@Override
	public List<ReviewVO> totalReview(ReviewVO vo) {
		// 리뷰 총 갯수
		return proMapper.totalReview(vo);
	}

	
	
}
