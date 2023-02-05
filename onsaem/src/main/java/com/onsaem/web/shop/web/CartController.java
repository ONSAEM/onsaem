package com.onsaem.web.shop.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onsaem.web.shop.service.CartService;
import com.onsaem.web.shop.service.CartVO;
import com.onsaem.web.shop.service.ProductService;
import com.onsaem.web.shop.service.ProductVO;

@Controller
@CrossOrigin(origins = "*")
public class CartController {

	@Autowired
	CartService cartService;
	@Autowired
	ProductService proService;

	CartVO cartVO=new CartVO();
	
	// 버튼클릭 장바구니담기
	@RequestMapping(value = "/cartAdd", method = RequestMethod.GET)
	public String cartAdd(Model model,CartVO vo, Authentication authentication) {	
		 UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		if ( Integer.toString(vo.getCartAmount()).equals("0") ) {	
			vo.setMemberId(userDetails.getUsername());
			vo.setCartAmount(1);
			vo.setProductId(vo.getProductId());					
		} else {
			vo.setMemberId(userDetails.getUsername());
			vo.setProductId(vo.getProductId());
			vo.setCartAmount((int)vo.getCartAmount());
			vo.setCartOption(vo.getCartOption());			
		}
		cartService.cartAdd(vo);
		return "redirect:/shop";
	}
	
	// 장바구니페이지이동,나의 장바구니리스트
		@RequestMapping(value = "/shop/shopMyCart", method = RequestMethod.GET)
		public String shopCart(Model model,Authentication authentication) {
			UserDetails userDetails = (UserDetails)authentication.getPrincipal();			
			cartVO.setMemberId(userDetails.getUsername());
			cartService.myCartList(cartVO);
			System.out.println(cartService.myCartList(cartVO));
			model.addAttribute("myCartList",cartService.myCartList(cartVO));
			/*
			 * List<ProductVO> list=new ArrayList<ProductVO>(); for(int
			 * i=0;i<cartService.myCartList(cartVO).size();i++) {
			 * list.addAll(proService.optionList(cartService.myCartList(cartVO).get(i).
			 * getProductId())); } model.addAttribute("optionList",list);
			 */
			return "content/shop/shopMyCart";
		}

	//장바구니 수량변경
		@RequestMapping(value = "/changeAmount", method = RequestMethod.POST)
		public String changeAmount(Model model,HttpServletRequest request) {
			HttpSession session=request.getSession();
			String data=(String)session.getAttribute("id");
			cartVO.setMemberId("user");
			
			model.addAttribute("myCartList",cartService.myCartList(cartVO));
			return "content/shop/shopCart";
		}
		
	//장바구니 삭제
		@RequestMapping(value = "/delCart", method = RequestMethod.POST)
		public String delCart(Model model,Authentication authentication,@RequestBody CartVO vo) {
			UserDetails userDetails = (UserDetails)authentication.getPrincipal();;
			vo.setMemberId(userDetails.getUsername());
			cartService.delCart(vo);			
			return "content/shop/shopCart";
		}
		
}
