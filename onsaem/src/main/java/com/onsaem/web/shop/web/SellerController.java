package com.onsaem.web.shop.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.shop.service.CartService;
import com.onsaem.web.shop.service.CartVO;
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
	public Integer endProduct(@RequestBody ProductVO vo, Authentication authentication) {
		return sellService.endProduct(vo);
	}

	// 판매자 주문목록
	@RequestMapping(value = "/sellerOrder", method = RequestMethod.GET)
	public String sellerOrder() {
		return "content/shop/sellerOrder";
	}
}
