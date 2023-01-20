package com.onsaem.web.shop.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.shop.service.CartService;
import com.onsaem.web.shop.service.CartVO;
import com.onsaem.web.shop.service.ProductService;
import com.onsaem.web.shop.service.ProductVO;

@Controller
@CrossOrigin(origins = "*")
public class SellerController {
	@Autowired
	ProductService proService;
	@Autowired
	CartService cartService;
	@Autowired
	MediaService mediaService;

	CartVO cartVo = new CartVO();
	LikeVO likeVo = new LikeVO();
	ProductVO proVo = new ProductVO();
	
	// 판매자 페이지로 이동
		@RequestMapping(value = "/sellerMain", method = RequestMethod.GET)
		public String seller(Model model,Authentication authentication) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			model.addAttribute("sellerList",proService.sellerList(userDetails.getUsername()));
			return "content/shop/sellerMain";
		}

		// 상품등록페이지로 이동
		@RequestMapping(value = "/addProductPage", method = RequestMethod.GET)
		public String addProductPage(Model model) {
			model.addAttribute("categoryList", proService.categoryList());
			return "content/shop/addProduct";
		}
}
