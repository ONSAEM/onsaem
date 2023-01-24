package com.onsaem.web.shop.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.RequestParamMapMethodArgumentResolver;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.shop.service.CartService;
import com.onsaem.web.shop.service.CartVO;
import com.onsaem.web.shop.service.OrderVO;
import com.onsaem.web.shop.service.ProductService;
import com.onsaem.web.shop.service.ProductVO;
import com.onsaem.web.shop.service.SellerService;

@Controller
@CrossOrigin(origins = "*")
public class SellerController {
	@Autowired
	ProductService proService;
	@Autowired
	CartService cartService;
	@Autowired
	MediaService mediaService;
	@Autowired
	SellerService sellService;

	CartVO cartVo = new CartVO();
	LikeVO likeVo = new LikeVO();
	ProductVO proVo = new ProductVO();

	// 판매자 페이지로 이동
	@RequestMapping(value = "/sellerMain", method = RequestMethod.GET)
	public String seller(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		model.addAttribute("sellerList", proService.sellerList(userDetails.getUsername()));
		model.addAttribute("countList", sellService.countList(userDetails.getUsername()));
		return "content/shop/sellerMain";
	}

	// 상품등록페이지로 이동
	@RequestMapping(value = "/addProductPage", method = RequestMethod.GET)
	public String addProductPage(Model model) {
		model.addAttribute("categoryList", proService.categoryList());
		return "content/shop/addProduct";
	}

	// 승인대기품목
	@RequestMapping(value = "/waitProduct", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> waitProduct(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.waitList(userDetails.getUsername());
	}

	// 전체품목
	@RequestMapping(value = "/sellerAllProduct", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> sellerAllProduct(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return proService.sellerList(userDetails.getUsername());
	}

	// 판매대기품목
	@RequestMapping(value = "/waitSell", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> waitSell(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.waitSell(userDetails.getUsername());
	}

	// 판매중
	@RequestMapping(value = "/selling", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> selling(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.selling(userDetails.getUsername());
	}

	// 품절
	@RequestMapping(value = "/endSell", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> endSell(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.endSell(userDetails.getUsername());
	}

	// 판매중지
	@RequestMapping(value = "/stopSell", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> stopSell(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.stopSell(userDetails.getUsername());
	}

	// 판매정지
	@RequestMapping(value = "/banSell", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> banSell(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.banSell(userDetails.getUsername());
	}

	// 전체조회
	@RequestMapping(value = "/arrayProductAll", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductVO> arrayProductAll(Authentication authentication, @RequestBody ProductVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		return sellService.arrayProductAll(vo);
	}

	// 상품명 조회
	@RequestMapping(value = "/arrayProductName", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductVO> arrayProductName(Authentication authentication, @RequestBody ProductVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		return sellService.arrayProductName(vo);
	}

	// 상품번호 조회
	@RequestMapping(value = "/arrayProductId", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductVO> arrayProductId(Authentication authentication, @RequestBody ProductVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		return sellService.arrayProductId(vo);
	}

	// 판매상태별 조회
	@RequestMapping(value = "/statusProduct", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> statusProduct(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.statusProduct(userDetails.getUsername());
	}

	// 수량별 조회
	@RequestMapping(value = "/amountProduct", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> amountProduct(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.amountProduct(userDetails.getUsername());
	}

	// 품목별 조회
	@RequestMapping(value = "/categoryProduct", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> categoryProduct(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.categoryProduct(userDetails.getUsername());
	}

	// 날짜별 조회
	@RequestMapping(value = "/dateProduct", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductVO> dateProduct(Authentication authentication, @RequestBody ProductVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		return sellService.dateProduct(vo);
	}

	// 품절처리
	@RequestMapping(value = "/endProduct", method = RequestMethod.POST)
	@ResponseBody
	public Integer endProduct(@RequestBody ProductVO vo) {
		return sellService.endProduct(vo);
	}

	// 판매자 주문목록
	@RequestMapping(value = "/sellerOrder", method = RequestMethod.GET)
	public String sellerOrder(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		model.addAttribute("orderList", sellService.orderList(userDetails.getUsername()));
		return "content/shop/sellerOrder";
	}

	// 품절처리
	@RequestMapping(value = "/shop/changeDate", method = RequestMethod.POST)
	@ResponseBody
	public List<OrderVO> changeDate(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.changeDate(startDate, endDate, userDetails.getUsername());
	}

	// 오늘검색
	@RequestMapping(value = "/shop/todaySearch", method = RequestMethod.POST)
	@ResponseBody
	public List<OrderVO> todaySearch(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.todaySearch(userDetails.getUsername());
	}

	// 일주일조회
	@RequestMapping(value = "/shop/weekSearch", method = RequestMethod.POST)
	@ResponseBody
	public List<OrderVO> weekSearch(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.weekSearch(userDetails.getUsername());
	}

	// 한달조회
	@RequestMapping(value = "/shop/monthSearch", method = RequestMethod.POST)
	@ResponseBody
	public List<OrderVO> monthSearch(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.monthSearch(userDetails.getUsername());
	}

	// 전체주문검색
	@RequestMapping(value = "/arrayOrderAll", method = RequestMethod.POST)
	@ResponseBody
	public List<OrderVO> arrayOrderAll(Authentication authentication, @RequestParam("data") String data) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.arrayOrderAll(userDetails.getUsername(), data);
	}

	// 구매자명검색
	@RequestMapping(value = "/shop/buyName", method = RequestMethod.POST)
	@ResponseBody
	public List<OrderVO> buyName(Authentication authentication, @RequestParam("data") String data) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.buyName(userDetails.getUsername(), data);
	}

	// 구매자ID검색
	@RequestMapping(value = "/shop/buyId", method = RequestMethod.POST)
	@ResponseBody
	public List<OrderVO> buyId(Authentication authentication, @RequestParam("data") String data) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.buyId(userDetails.getUsername(), data);
	}

	// 주문번호검색
	@RequestMapping(value = "/shop/orderNumber", method = RequestMethod.POST)
	@ResponseBody
	public List<OrderVO> orderNumber(Authentication authentication, @RequestParam("data") String data) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.orderNumber(userDetails.getUsername(), data);
	}

	// 상품번호검색
	@RequestMapping(value = "/shop/productId", method = RequestMethod.POST)
	@ResponseBody
	public List<OrderVO> productId(Authentication authentication, @RequestParam("data") String data) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.productId(userDetails.getUsername(), data);
	}

	// 교환/반품검색
	@RequestMapping(value = "/orderExchange", method = RequestMethod.POST)
	@ResponseBody
	public List<OrderVO> orderExchange(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.orderExchange(userDetails.getUsername());
	}

	// 배송완료조회
	@RequestMapping(value = "/endShipping", method = RequestMethod.POST)
	@ResponseBody
	public List<OrderVO> endShipping(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.endShipping(userDetails.getUsername());
	}
	
	// 배송완료조회
		@RequestMapping(value = "/shipping", method = RequestMethod.POST)
		@ResponseBody
		public List<OrderVO> shipping(Authentication authentication) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			return sellService.shipping(userDetails.getUsername());
		}
		
		// 배송완료조회
				@RequestMapping(value = "/sell/waitOrder", method = RequestMethod.POST)
				@ResponseBody
				public List<OrderVO> waitOrder(Authentication authentication) {
					UserDetails userDetails = (UserDetails) authentication.getPrincipal();
					return sellService.waitOrder(userDetails.getUsername());
				}
}
