package com.onsaem.web.shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin(origins="*")
public class ProductController {
	
	@RequestMapping(value="/shop",method=RequestMethod.GET)
	public String shopList(Model model) {
		return "content/shop/ShopMain";
	}
	
	@RequestMapping(value="/shopDetail",method=RequestMethod.GET)
	public String shopSelect(Model model) {
		return "content/shop/ShopDetail";
	}
	@RequestMapping(value="/shopCart",method=RequestMethod.GET)
	public String shopCart(Model model) {
		return "content/shop/ShopCart";
	}
	@RequestMapping(value="/check",method=RequestMethod.GET)
	public String shopCheck(Model model) {
		return "content/shop/ShopCheck";
	}
}
