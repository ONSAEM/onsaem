package com.onsaem.web.shop.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.common.service.PaymentVO;
import com.onsaem.web.member.service.MemberVO;
import com.onsaem.web.shop.service.CartService;
import com.onsaem.web.shop.service.OrderDetailVO;
import com.onsaem.web.shop.service.OrderService;
import com.onsaem.web.shop.service.OrderVO;
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

	OrderVO orderVO = new OrderVO();
	
	//주문완료페이지이동
	@RequestMapping(value = "/shop/buyEnd", method = RequestMethod.GET)
	public String buyEnd() {		
		return "content/shop/buyEnd";
	}

	// 주문결제페이지이동
	@RequestMapping(value = "/buyProduct", method = RequestMethod.GET)
	public String buyProduct(Model model, ProductVO vo, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		MemberVO memberVO = new MemberVO();
		memberVO.setMemberId(userDetails.getUsername());
		model.addAttribute("userData", orderService.userData(memberVO));
		model.addAttribute("productList", proService.selectPro(vo.getProductId()));
		return "content/shop/shopBuy";
	}

	// 나의 주문목록(가짜)
	@RequestMapping(value = "/myOrder", method = RequestMethod.GET)
	public String myOrder(Model model, OrderVO orderVO, Authentication authentication) {		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		orderVO.setMemberId(userDetails.getUsername());
		model.addAttribute("myOrderList", orderService.myOrderList(orderVO));
		model.addAttribute("myReviewList",proService.myReviewList(userDetails.getUsername()));
		return "content/shop/myOrder";
	}
	
	//나의 주문목록
	@RequestMapping(value = "/shop/shopMyOrder", method = RequestMethod.GET)
	public String shopMyOrder(Model model, OrderVO orderVO, Authentication authentication) {		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		orderVO.setMemberId(userDetails.getUsername());
		model.addAttribute("myOrderList", orderService.myOrderList(orderVO));
		model.addAttribute("myReviewList",proService.myReviewList(userDetails.getUsername()));
		return "content/shop/shopMyOrder";
	}
	
	// 내 리뷰리스트(수정해야함)
	@RequestMapping(value = "/shop/myReviewList", method = RequestMethod.GET)
	public String myReviewList(Authentication authentication) {		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		proService.myReviewList(userDetails.getUsername());
		return "content/shop/myOrder";
	}

	// 주문하기
	@RequestMapping(value = "/shop/orderProduct", method = RequestMethod.POST)
	@ResponseBody
	public int orderProduct(@RequestBody OrderVO odVO, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		odVO.setMemberId(userDetails.getUsername());
		orderService.orderProduct(odVO);
		return orderService.orderDetail(odVO);
	}

	// 결제하기
	@RequestMapping(value = "/shop/insertOrder", method = RequestMethod.POST)
	@ResponseBody
	public int insertOrder(@RequestBody PaymentVO vo, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		vo.setPayerId(userDetails.getUsername());		
		return orderService.insertOrder(vo);
	}
	
	//교환,환불요청
	@RequestMapping(value = "/shop/orderExchange", method = RequestMethod.POST)
	@ResponseBody
	public int orderExchange(@RequestBody OrderVO vo) {				
		return orderService.orderExchange(vo);
	}
}