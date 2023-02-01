package com.onsaem.web.shop.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.shop.service.AdminShopService;
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
	AdminShopService service;

	// 승인처리
	@RequestMapping(value = "/admin/approProduct", method = RequestMethod.POST)
	@ResponseBody
	public String adminApproProduct(ProductVO vo) {
		vo.setProductStatus("판매중");
		service.adminApproProduct(vo);
		return "성공";
	}

	// 관리자상품관리페이지이동
	@RequestMapping(value = "/shop/adminProduct", method = RequestMethod.GET)
	public String adminProduct(Model model) {
		return "content/shop/adminProduct";
	}

	// 승인대기품목
	@RequestMapping(value = "/shop/approProduct", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> approProduct(ProductVO vo, Paging paging) {
		return proService.proList(vo, paging); // 승인대기품목
	}

	// 추가이미지가져오기
	@RequestMapping(value = "/admin/addImg", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductVO> addImg(String data) {	
		return proService.addImg(data); // 추가이미지
	}
	
	//친환경이미지가져오기
	@RequestMapping(value = "/admin/natureImg", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductVO> natureImg(String data) {		
		return proService.natureImg(data); 
	}
}
