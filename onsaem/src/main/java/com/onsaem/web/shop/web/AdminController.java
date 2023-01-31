package com.onsaem.web.shop.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.shop.service.CartService;
import com.onsaem.web.shop.service.ProductService;
import com.onsaem.web.shop.service.ProductVO;

/**
 * 
 * @author 이재원 관리자쇼핑몰관리
 *
 */
@Controller
@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	ProductService proService;
	@Autowired
	CartService cartService;
	@Autowired
	MediaService mediaService;

	// 관리자상품관리페이지이동
	@RequestMapping(value = "/shop/adminProduct", method = RequestMethod.GET)
	public String adminProduct(Model model) {		
		return "content/shop/adminProduct";
	}

	// 승인대기품목
	@RequestMapping(value = "/shop/approProduct", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> approProduct(ProductVO vo,Paging paging) {			
		return proService.proList(vo,paging); // 승인대기품목
	}

}
