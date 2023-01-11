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
public class ProductController {
	@Autowired
	ProductService proService;
	@Autowired
	CartService cartService;
	
	CartVO vo=new CartVO();
	

	// 쇼핑몰페이지이동,최신순,인기순 목록나열
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String shopMain(Model model, 
		@RequestParam(value = "data", required = false) String data,HttpServletRequest request) {
		HttpSession session=request.getSession();
		if (data != null && data.equals("popularity")) {			
			vo.setMemberId((String)session.getAttribute("id"));			
			model.addAttribute("cartList", cartService.cartList(vo)); // 장바구니 수량가져오기 위한 리스트
			model.addAttribute("likeList", proService.likeList()); // 찜 수량가져오기 위한 리스트
			model.addAttribute("productList", proService.popList());
			return "content/shop/shopMain";
		} else {			
			vo.setMemberId((String)session.getAttribute("id"));
			System.out.println(proService.proList());
			model.addAttribute("cartList", cartService.cartList(vo)); // 장바구니 수량가져오기 위한 리스트
			model.addAttribute("likeList", proService.likeList()); // 찜 수량가져오기 위한 리스트
			model.addAttribute("productList", proService.proList());
			return "content/shop/shopMain";
		}
	}

	// 상세설명페이지이동
	@RequestMapping(value = "/shopDetail", method = RequestMethod.GET)
	public String shopSelect(Model model,@RequestParam(value = "data", required = false) String data) {		
		model.addAttribute("productList", proService.selectPro(data));//상품데이터가져오기
		model.addAttribute("imgList",proService.addImg(data)); 
		return "content/shop/shopDetail";
	}

	// 장바구니페이지이동
	@RequestMapping(value = "/shopCart", method = RequestMethod.GET)
	public String shopCart(Model model) {
		return "content/shop/shopCart";
	}

	// 결제페이지이동
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String shopCheck(Model model) {
		return "content/shop/shopCheck";
	}

	// 카테고리별 목록리스트
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String shopCategory(Model model, @RequestParam(value = "data", required = false) String data
			,HttpServletRequest request) {
		HttpSession session=request.getSession();
		vo.setMemberId((String)session.getAttribute("id"));
		model.addAttribute("productList", proService.proCategory(data));
		model.addAttribute("cartList", cartService.cartList(vo)); // 장바구니 수량가져오기 위한 리스트
		model.addAttribute("likeList", proService.likeList()); // 찜 수량가져오기 위한 리스트
		return "content/shop/shopMain";
	}

	// 검색 목록리스트
	@RequestMapping(value = "/searchProduct", method = RequestMethod.POST)
	public String searchProduct(Model model, @RequestParam(value = "data", required = false) String data
			,HttpServletRequest request) {
		HttpSession session=request.getSession();
		vo.setMemberId((String)session.getAttribute("id"));
		model.addAttribute("productList", proService.searchProduct(data));
		model.addAttribute("cartList", cartService.cartList(vo)); // 장바구니 수량가져오기 위한 리스트
		model.addAttribute("likeList", proService.likeList()); // 찜 수량가져오기 위한 리스트
		return "content/shop/shopMain";
	}

	// 찜클릭 찜담기
	@RequestMapping(value = "/likeAdd", method = RequestMethod.GET)
	public String cartAdd(Model model, @RequestParam(value = "data", required = false) String data,HttpServletRequest request) {		
		HttpSession session = request.getSession();				
		proService.likeAdd(data);
		return "redirect:/shop";
	}
	
	// 찜클릭 삭제
		@RequestMapping(value = "/likeDel", method = RequestMethod.GET)
		public String cartDel(Model model, @RequestParam(value = "data", required = false) String data ) {
			System.out.println("====================" + data);
			proService.likeDel(data);
			return "redirect:/shop";
		}

}
