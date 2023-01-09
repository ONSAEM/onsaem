package com.onsaem.web.shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onsaem.web.shop.mapper.ProductMapper;
import com.onsaem.web.shop.service.ProductService;

@Controller
@CrossOrigin(origins="*")
public class ProductController {	
	@Autowired ProductService proService;	
	//쇼핑몰페이지이동,최신순,인기순 목록나열
	@RequestMapping(value="/shop",method=RequestMethod.GET)	
	public String shopMain(Model model, @RequestParam(value="data",required = false) String data ) {
		System.out.println("===================="+data);
		if(data !=null && data.equals("popularity")) {
			System.out.println("==========="+proService.popList());
			model.addAttribute("productList",proService.popList());
			return "content/shop/shopMain";
		}else {
			System.out.println(proService.proList());
			model.addAttribute("productList",proService.proList());
			return "content/shop/shopMain";
		}		
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
	//카테고리별 목록리스트
	@RequestMapping(value="/category",method=RequestMethod.GET)
	public String shopCategory(Model model,@RequestParam(value="data",required = false) String data ) {
		System.out.println(data);
		model.addAttribute("productList",proService.proCategory(data));
		return "content/shop/shopMain";
	}
	//검색 목록리스트
	@RequestMapping(value="/searchProduct",method=RequestMethod.POST)	
	public String searchProduct(Model model, @RequestParam(value="data",required = false) String data ) {
		System.out.println("===================="+data);		
		model.addAttribute("productList",proService.searchProduct(data));
		return "content/shop/shopMain";		
	}	
	
	
}
