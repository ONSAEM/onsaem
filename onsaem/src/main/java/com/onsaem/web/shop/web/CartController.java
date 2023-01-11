package com.onsaem.web.shop.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	@Autowired
	ProductService proService;

	// 버튼클릭 장바구니담기
	@RequestMapping(value = "/cartAdd", method = RequestMethod.GET)
	public String cartAdd(Model model,CartVO vo, HttpServletRequest request) {
		CartVO cartVo = new CartVO();
		HttpSession session = request.getSession();
		if ( Integer.toString(vo.getCartAmount()) == null) {			
			cartVo.setMemberId((String)session.getAttribute("id"));
			cartVo.setCartAmount(1);
			cartVo.setProductId(vo.getProductId());
			cartService.cartAdd(cartVo);
			return "redirect:/shop";
		} else {
			cartVo.setMemberId((String)session.getAttribute("id"));
			cartVo.setProductId(vo.getProductId());
			cartVo.setCartAmount((int)vo.getCartAmount());
			cartVo.setCartOption(vo.getCartOption());
			cartService.cartAdd(cartVo);
			model.addAttribute("productList", proService.selectPro(vo.getProductId()));// 상품데이터가져오기
			model.addAttribute("imgList", proService.addImg(vo.getProductId()));
			return "content/shop/shopDetail";
		}
	}

}
