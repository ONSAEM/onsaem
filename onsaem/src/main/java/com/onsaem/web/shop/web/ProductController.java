package com.onsaem.web.shop.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.ReportVO;
import com.onsaem.web.common.service.ReviewVO;
import com.onsaem.web.shop.service.CartService;
import com.onsaem.web.shop.service.CartVO;
import com.onsaem.web.shop.service.OptionVO;
import com.onsaem.web.shop.service.OrderVO;
import com.onsaem.web.shop.service.ProductService;
import com.onsaem.web.shop.service.ProductVO;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;

/**
 * 
 * @author 이재원 상품관리
 *
 */
@Controller
@CrossOrigin(origins = "*")
public class ProductController {
	@Autowired
	ProductService proService;
	@Autowired
	CartService cartService;
	@Autowired
	MediaService mediaService;

	CartVO cartVo = new CartVO();
	LikeVO likeVo = new LikeVO();
	ProductVO proVo = new ProductVO();

	// 뷰테스트
	@RequestMapping(value = "/shop/test", method = RequestMethod.GET)
	public String vueTest(Model model) {
		proService.approProduct(); // 승인대기품목(아직안뿌려줌)
		return "content/shop/test";
	}

	// 리뷰작성가능여부
	@RequestMapping(value = "/shop/compareReview", method = RequestMethod.POST)
	@ResponseBody
	public String compareReview(Authentication authentication, OrderVO vo) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setMemberId(userDetails.getUsername());
		if ((proService.compareReview(vo)).isEmpty()) {
			return "불가능";
		} else {
			return "가능";
		}
	}

	// 주간베스트 데이터 가져오기
	@RequestMapping(value = "/shop/weekBestProduct", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductVO> weekBestProduct(@RequestBody List<ProductVO> vo) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < vo.size(); i++) {
			list.add(vo.get(i).getProductId());
		}
		return proService.selectProduct(list);
	}
	

	// 쇼핑몰페이지이동,최신순,인기순 목록나열
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String shopMain(Model model, @RequestParam(value = "data", required = false) String data, CartVO cartVo,
			Authentication authentication, Paging paging, ProductVO vo) {
		if (authentication != null) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			cartVo.setMemberId(userDetails.getUsername());
			likeVo.setMemberId(userDetails.getUsername());
			model.addAttribute("cartList", cartService.cartList(cartVo)); // 장바구니 수량가져오기 위한 리스트
			model.addAttribute("likeList", proService.likeList(likeVo)); // 찜 수량가져오기 위한 리스트
		}
		model.addAttribute("categoryList", proService.categoryList()); // 카테고리 리스트
		if (data != null && data.equals("popularity")) {
			model.addAttribute("productList", proService.popList(vo, paging));
		} else {
			model.addAttribute("productList", proService.proList(vo, paging));
			model.addAttribute("paging", paging);
		}
		//주간베스트목록보내주기
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < proService.weekBest().size(); i++) {
			list.add(proService.weekBest().get(i).getProductId());
		}
		model.addAttribute("weekList",proService.selectProduct(list)); 
		return "content/shop/shopMain";
	}

	// 상세설명페이지이동
	@RequestMapping(value = "/shopDetail", method = RequestMethod.GET)
	public String shopSelect(Model model, @RequestParam(value = "data", required = false) String data) {
		model.addAttribute("productList", proService.selectPro(data));// 상품데이터가져오기
		model.addAttribute("imgList", proService.addImg(data));// 추가이미지가져오기
		model.addAttribute("natureImg", proService.natureImg(data));// 친환경이미지가져오기
		model.addAttribute("reviewList", proService.reviewList(data));// 상품리뷰리스트가져오기
		model.addAttribute("optionList", proService.optionList(data));// 옵션가져오기
		return "content/shop/shopDetail";
	}

	// 리뷰갯수가져오기
	@RequestMapping(value = "/shop/totalReview", method = RequestMethod.GET)
	@ResponseBody
	public List<ReviewVO> totalReview(ReviewVO vo) {		
		return proService.totalReview(vo);
	}

	// 결제페이지이동
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String shopCheck(Model model) {
		return "content/shop/shopCheck";
	}

	// 카테고리별 목록리스트
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String shopCategory(Model model, @RequestParam(value = "data", required = false) String data,
			Authentication authentication, Paging paging, ProductVO vo) {
		if (authentication != null) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			cartVo.setMemberId(userDetails.getUsername());
			likeVo.setMemberId(userDetails.getUsername());
			model.addAttribute("cartList", cartService.cartList(cartVo)); // 장바구니 수량가져오기 위한 리스트
			model.addAttribute("likeList", proService.likeList(likeVo)); // 찜 수량가져오기 위한 리스트
		}
		model.addAttribute("productList", proService.proCategory(data, paging, vo));
		model.addAttribute("categoryList", proService.categoryList()); // 카테고리 리스트
		model.addAttribute("paging", paging);
		return "content/shop/shopMain";
	}

	// 검색 목록리스트
	@RequestMapping(value = "/searchProduct", method = RequestMethod.POST)
	public String searchProduct(Model model, @RequestParam(value = "data", required = false) String data,
			Authentication authentication, Paging paging, ProductVO vo) {
		if (authentication != null) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			cartVo.setMemberId(userDetails.getUsername());
			likeVo.setMemberId(userDetails.getUsername());
			model.addAttribute("cartList", cartService.cartList(cartVo)); // 장바구니 수량가져오기 위한 리스트
			model.addAttribute("likeList", proService.likeList(likeVo)); // 찜 수량가져오기 위한 리스트
		}
		model.addAttribute("productList", proService.searchProduct(data, paging, vo));
		return "content/shop/shopMain";
	}

	// 찜클릭 찜담기
	@RequestMapping(value = "/likeAdd", method = RequestMethod.GET)
	public String likeAdd(Model model, @RequestParam(value = "data", required = false) String data,
			Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		likeVo.setMemberId(userDetails.getUsername());
		likeVo.setGroupId(data);
		proService.likeAdd(likeVo);
		return "redirect:/shop";
	}

	// 버튼찜클릭 삭제
	@RequestMapping(value = "/likeDel", method = RequestMethod.GET)
	public String cartDel(Model model, @RequestParam(value = "data", required = false) String data,
			Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		likeVo.setMemberId(userDetails.getUsername());
		likeVo.setGroupId(data);
		proService.likeDel(likeVo);
		return "redirect:/shop";
	}

	// 상품등록
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct(Model model, @Nullable ProductVO vo, OptionVO ovo, Authentication authentication,
			MultipartFile[] applyFile, MultipartFile[] natureFile) throws IllegalStateException, IOException {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		vo.setMemberId(userDetails.getUsername());
		proService.addProduct(vo);
		ovo.setProductId(vo.getProductId());
		System.out.println("====================="+applyFile[0].getOriginalFilename());
		// 옵션목록 넣기
		String[] array = ovo.getOptionContent().split(",");
		for (int i = 0; i < array.length; i++) {
			ovo.setOptionContent(array[i]);
			proService.addOption(ovo);
		}

		MediaVO mvo = new MediaVO();
		mvo.setGroupId(vo.getProductId());
		mvo.setGroups("쇼핑몰");

		// 사진구분해서 넣기
		for (int i = 0; i < applyFile.length; i++) {
			if (i == 0) {
				mvo.setSubGroup("대표이미지");
			} else {
				mvo.setSubGroup("추가이미지");
			}
			MultipartFile[] upload = { applyFile[i] };
			mediaService.uploadMedia(upload, mvo);
		}

		// 친환경인증증서 넣기
		mvo.setSubGroup("친환경인증증서");
		mediaService.uploadMedia(natureFile, mvo);

		return "redirect:/shop/shopAddProduct";
	}

	// 상품신고
	@RequestMapping(value = "/addBan", method = RequestMethod.POST)
	@ResponseBody
	public int addBan(Model model, @RequestBody ReportVO vo, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setFromId(userDetails.getUsername());
		return proService.addBan(vo);
	}

	// 찜리스트 페이지이동
	@RequestMapping(value = "/likeList", method = RequestMethod.GET)
	public String likeList(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		likeVo.setLikeId(userDetails.getUsername());
		model.addAttribute("likeList", proService.likeList(likeVo));
		return "content/shop/shopLike";
	}

	// 버튼찜클릭 삭제
	@RequestMapping(value = "/delMyLike", method = RequestMethod.POST)
	@ResponseBody
	public int delMyLike(Model model, @RequestBody ProductVO vo, Authentication authentication) {
		return proService.delMyLike(vo);
	}

	// 최근본상품
	@RequestMapping(value = "/watchProduct", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductVO> watchProduct(Model model, @RequestBody ProductVO vo, Authentication authentication) {
		return proService.watchProduct(vo);
	}

	// 리뷰작성
	@RequestMapping(value = "/addReview", method = RequestMethod.POST)
	@ResponseBody
	public int addReview(Model model, @RequestBody ReviewVO vo, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setWriterId(userDetails.getUsername());
		proService.reviewPoint(userDetails.getUsername());// 리뷰포인트 적립
		proService.addReview(vo);// 리뷰작성
		return proService.updateStar(vo);// 리뷰별점 업데이트

	}

}
