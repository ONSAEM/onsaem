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
import com.onsaem.web.common.service.ReviewVO;
import com.onsaem.web.shop.service.CartService;
import com.onsaem.web.shop.service.CartVO;
import com.onsaem.web.shop.service.OrderVO;
import com.onsaem.web.shop.service.ProductService;
import com.onsaem.web.shop.service.ProductVO;
import com.onsaem.web.shop.service.SellerService;

/**
 * 
 * @author 이재원 판매자 상품관리
 *
 */
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

	// 상품 날짜조회
	@RequestMapping(value = "/shop/dateProduct", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductVO> dateProduct(Authentication authentication, String startDate, String endDate, ProductVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		System.out.println(vo);
		System.out.println(startDate);
		System.out.println(endDate);

		return sellService.dateProduct(vo, startDate, endDate);
	}

	// 판매자 리뷰댓글달기
	@RequestMapping(value = "/shop/sellerReviewContent", method = RequestMethod.POST)
	@ResponseBody
	public int sellerReviewContent(Authentication authentication, ReviewVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setWriterId(userDetails.getUsername());
		vo.setGroups("쇼핑몰");
		return sellService.sellerReviewContent(vo);
	}

	// 판매자 리뷰페이지이동(가짜)
	@RequestMapping(value = "/sellerReview", method = RequestMethod.GET)
	public String sellerReview(Authentication authentication, Model model) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		model.addAttribute("reviewList", sellService.sellerReview(userDetails.getUsername()));
		return "content/shop/sellerReview";
	}

	// 판매자 리뷰페이지이동
	@RequestMapping(value = "shop/shopSellerReview", method = RequestMethod.GET)
	public String shopSellerReview() {
		return "content/shop/shopSellerReview";
	}

	// 판매자 교환/환불페이지이동
	@RequestMapping(value = "/shop/shopSellerExchange", method = RequestMethod.GET)
	public String shopSllerExchange(Authentication authentication) {
		return "content/shop/shopSellerExchange";
	}

	// 판매자 교환/환불 리스트
	@RequestMapping(value = "/shop/sellerExchangeList", method = RequestMethod.GET)
	@ResponseBody
	public List<OrderVO> sellerExchangeList(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.orderExchange(userDetails.getUsername());
	}

	// 주문상태변경
	@RequestMapping(value = "/shop/sumbitShipping", method = RequestMethod.POST)
	@ResponseBody
	public int sumbitShipping(Authentication authentication, @RequestBody OrderVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		vo.setOrderStatus("배송중");
		return sellService.sumbitShipping(vo);
	}

	// 판매자 페이지로 이동
	@RequestMapping(value = "/sellerMain", method = RequestMethod.GET)
	public String seller(Model model, Authentication authentication, ProductVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		model.addAttribute("sellerList", proService.sellerList(vo));
		model.addAttribute("countList", sellService.countList(userDetails.getUsername()));
		return "content/shop/sellerMain";
	}

	// 판매자 페이지로 이동(찐)
	@RequestMapping(value = "/shop/shopSellerMain", method = RequestMethod.GET)
	public String shopSellerMain(Model model, Authentication authentication, ProductVO vo) {
		return "content/shop/shopSellerMain";
	}

	// 판매자 페이지로 이동
	@RequestMapping(value = "/shop/countList", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> seller(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.countList(userDetails.getUsername());
	}

	// 판매자 상품가져오기
	@RequestMapping(value = "/shop/sellList", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> sellList(Authentication authentication, ProductVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		return proService.sellerList(vo);
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
	public List<ProductVO> waitProduct(Authentication authentication, ProductVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		vo.setProductStatus("승인대기");
		return proService.sellerList(vo);
	}

	// 전체품목
	@RequestMapping(value = "/sellerAllProduct", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> sellerAllProduct(Authentication authentication, ProductVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		return proService.sellerList(vo);
	}

	// 판매대기품목
	@RequestMapping(value = "/waitSell", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> waitSell(Authentication authentication, ProductVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		vo.setProductStatus("판매대기");
		return proService.sellerList(vo);
	}

	// 판매중
	@RequestMapping(value = "/selling", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> selling(Authentication authentication, ProductVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		vo.setProductStatus("판매중");
		return proService.sellerList(vo);
	}

	// 품절
	@RequestMapping(value = "/endSell", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> endSell(Authentication authentication, ProductVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		vo.setProductStatus("품절");
		return proService.sellerList(vo);
	}

	// 판매중지
	@RequestMapping(value = "/stopSell", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> stopSell(Authentication authentication, ProductVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		vo.setProductStatus("판매중지");
		return proService.sellerList(vo);
	}

	// 판매정지
	@RequestMapping(value = "/banSell", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductVO> banSell(Authentication authentication, ProductVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		vo.setProductStatus("판매정지");
		return proService.sellerList(vo);
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

	// 품절처리
	@RequestMapping(value = "/endProduct", method = RequestMethod.POST)
	@ResponseBody
	public Integer endProduct(@RequestBody ProductVO vo) {
		return sellService.endProduct(vo);
	}

	// 판매자 주문목록 페이지 이동
	@RequestMapping(value = "/shop/shopSellerOrder", method = RequestMethod.GET)
	public String shopSellerOrder() {
		return "content/shop/shopSellerOrder";
	}

	// 판매자 주문목록 가져오기
	@RequestMapping(value = "/shop/sellerOrderList", method = RequestMethod.GET)
	@ResponseBody
	public List<OrderVO> sellerOrderList(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return sellService.orderList(userDetails.getUsername());
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
