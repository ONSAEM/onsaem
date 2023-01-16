package com.onsaem.web.shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onsaem.web.member.service.MemberVO;
import com.onsaem.web.shop.service.CartService;
import com.onsaem.web.shop.service.OrderService;
import com.onsaem.web.shop.service.ProductService;
import com.onsaem.web.shop.service.ProductVO;


@Controller
@CrossOrigin(origins = "*")
public class OrderController {
	
	@Autowired
	ProductService proService;
	@Autowired
	CartService cartService;
	@Autowired
	OrderService orderService;
	// 주문결제페이지이동
		@RequestMapping(value = "/buyProduct", method = RequestMethod.GET)
		public String buyProduct(Model model, ProductVO vo, Authentication authentication) {	
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			MemberVO memberVO=new MemberVO();
			memberVO.setMemberId(userDetails.getUsername());
			model.addAttribute("userData",orderService.userData(memberVO));
			return "content/shop/shopBuy";
		}
}
