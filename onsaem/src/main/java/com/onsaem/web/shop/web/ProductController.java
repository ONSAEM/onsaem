package com.onsaem.web.shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onsaem.web.shop.mapper.ProductMapper;
import com.onsaem.web.shop.service.ProductVO;

@Controller
@CrossOrigin(origins="*")
public class ProductController {	
	@Autowired ProductMapper proMapper;	
	//쇼핑몰페이지이동
	@RequestMapping(value="/shop",method=RequestMethod.GET)
	public String shopList(Model model) {
		ProductVO proVo;
		System.out.println(proMapper.proList());
		return "content/shop/shopMain";
	}
	//상세설명페이지이동
	@RequestMapping(value="/shopDetail",method=RequestMethod.GET)
	public String shopSelect(Model model) {
		return "content/shop/shopDetail";
	}
	//장바구니페이지이동
	@RequestMapping(value="/shopCart",method=RequestMethod.GET)
	public String shopCart(Model model) {
		return "content/shop/shopCart";
	}
	//결제페이지이동
	@RequestMapping(value="/check",method=RequestMethod.GET)
	public String shopCheck(Model model) {
		return "content/shop/shopCheck";
	}
	
//	@RequestMapping(value="/shop",method=RequestMethod.GET)
//	public String proList(Model model){
//		
//		return "content/shop/shopMain"
//	}
}
