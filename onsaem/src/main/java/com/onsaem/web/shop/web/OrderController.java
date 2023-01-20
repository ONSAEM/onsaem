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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.member.service.MemberVO;
import com.onsaem.web.shop.service.CartService;
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
	
	OrderVO orderVO=new OrderVO();

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

	// 나의 주문목록
	@RequestMapping(value = "/myOrder", method = RequestMethod.GET)
	public String myOrder(Model model, ProductVO vo, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		orderVO.setMemberId(userDetails.getUsername());		
		model.addAttribute("myOrderList",orderService.myOrderList(orderVO));
		
		return "content/shop/myOrder";
	}
	
	//결제
	@RequestMapping("/orderPay")
	@ResponseBody
	public String orderPay() {		 
		try {
			URL 주소 = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection 서버연결=(HttpURLConnection) 주소.openConnection();			
			서버연결.setRequestMethod("POST");		
			서버연결.setRequestProperty("Authorization", "KakaoAK f0a0bf2e20b21bff1334f2bd2d56ec9a");
			서버연결.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			String 파라미터="cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id"
					+ "&item_name=초코파이&quantity=1&total_amount=2200&vat_amount=200&tax_free_amount=0&approval_url=https://developers.kakao.com/success"
					+ "&fail_url=https://developers.kakao.com/fail&cancel_url=https://developers.kakao.com/cancel";
			OutputStream 주는애=서버연결.getOutputStream();
			DataOutputStream 데이터주는애=new DataOutputStream(주는애);
			데이터주는애.writeBytes(파라미터);
			데이터주는애.close();
			int 결과=서버연결.getResponseCode();
			InputStream 받는애;
			if(결과==200) {
				받는애=서버연결.getInputStream();
			}else {
				받는애=서버연결.getErrorStream();
			}
			InputStreamReader 읽는애 = new InputStreamReader(받는애);
			BufferedReader 형변환하는애 = new BufferedReader(읽는애);
			return 형변환하는애.readLine();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{\"result\":\"NO\"}";
		
		
		
	}

}