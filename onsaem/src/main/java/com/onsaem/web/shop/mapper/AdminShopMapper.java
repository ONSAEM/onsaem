package com.onsaem.web.shop.mapper;

import com.onsaem.web.shop.service.ProductVO;

public interface AdminShopMapper {
	//승인처리하기
	int adminApproProduct(ProductVO vo);
}
