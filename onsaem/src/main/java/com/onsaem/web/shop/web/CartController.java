package com.onsaem.web.shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onsaem.web.shop.service.CartService;
import com.onsaem.web.shop.service.CartVO;
import com.onsaem.web.shop.service.ProductService;

@Controller
@CrossOrigin(origins = "*")
public class CartController {

	@Autowired
	CartService cartService;
	ProductService proService;
	CartVO cartVo;

	// 버튼클릭 장바구니담기
	@RequestMapping(value = "/cartAdd", method = RequestMethod.GET)
	public String cartAdd(Model model, @RequestParam(value = "data", required = false) String data,@RequestParam(value = "amount", required = false) String amount) {
		if(amount==null) {
			System.out.println("====================" + data);
			cartVo.setCartAmount(1);
			cartVo.setProductId(data);
			cartService.cartAdd(cartVo);		
			return "redirect:/shop";
		}else {
			System.out.println("======================"+amount);
			System.out.println("====================" + data);			
			cartVo.setProductId(data);
			System.out.println("***********"+cartVo);
			cartVo.setCartAmount(Integer.parseInt(amount));
			cartService.cartAdd(cartVo);
			
			model.addAttribute("productList", proService.selectPro(data));//상품데이터가져오기
			model.addAttribute("imgList",proService.addImg(data)); 
			return "content/shop/shopDetail";					
		}		
	}
	
	
}
