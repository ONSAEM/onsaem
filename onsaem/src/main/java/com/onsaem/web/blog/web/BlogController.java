package com.onsaem.web.blog.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.blog.service.BlogService;
import com.onsaem.web.blog.service.BlogVO;
import com.onsaem.web.blog.service.BlogWriteService;
import com.onsaem.web.blog.service.CategoriesVO;
import com.onsaem.web.common.service.LikeVO;

/**
 * 
 * @author 정호경
 * 사용자가 블로그 관리 
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	BlogService blogService;
	@Autowired
	BlogWriteService blogWriteService;
	
	// 블로그 관리 페이지로 이동
	@RequestMapping(value="/myblog/blogTask", method=RequestMethod.GET)
	public String getBlogInfo(Model model, String blogId) {
			model.addAttribute("blogInfos", blogService.getBlogInfo(blogId));
			System.out.println("블로그 인포: "+model.getAttribute("blogInfos"));
		return "content/blog/blogTask";
	}
	// 블로그 통계 페이지로 이동
	@RequestMapping(value="/myblog/blogChart", method=RequestMethod.GET)
	public String getBlogChart(Model model, String blogId) {
		
		return "content/blog/blogChart";
	}
	// 블로그 구독 관리 페이지로 이동
	@RequestMapping(value="/myblog/blogSubscribe", method=RequestMethod.GET)
	public String getBlogSub(Model model, String blogId, LikeVO vo) {
		vo.setGroupId(blogId);
		vo.setMemberId(blogId);
		model.addAttribute("subMeList", blogService.subMeList(vo)); // 나를 구독한
		model.addAttribute("mySubList", blogService.mySubList(vo)); // 내가 구독한
		return "content/blog/blogSubscribe";
	}
	// 블로그 카테고리 관리 페이지로 이동
	@RequestMapping(value="/myblog/blogCategory", method=RequestMethod.GET)
	public String getBlogCate(Model model, String blogId, CategoriesVO vo) {
		vo.setBlogId(blogId); // 세션에 있는 값이랑 비교해서 본인 여부 확인 필요
		model.addAttribute("cateList",blogWriteService.cateList(vo));
		return "content/blog/blogCategory";
	}
	
	// 구독 추가
	@RequestMapping(value = "/addSub", method = RequestMethod.POST)
	@ResponseBody
	public LikeVO addBlogLike(LikeVO vo, Authentication authentication) {		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String id = userDetails.getUsername();
		vo.setMemberId(id);
		blogService.addSub(vo);
		return vo;
	}
	// 구독 삭제
	@RequestMapping(value = "/delSub", method = RequestMethod.POST)
	@ResponseBody
	public LikeVO delBlogLike(LikeVO vo, Authentication authentication) {
//		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
//		String id = userDetails.getUsername();		
//		vo.setMemberId(id);
		blogService.delSub(vo);
		return vo;
	}
	
	// 블로그 정보 수정
	// 일단 사진 다 넣고 있으면 status를 false로 바꾸고, 없으면 그냥 true로 넣어주기~
	@RequestMapping(value = "/updateInfo", method=RequestMethod.POST)
	@ResponseBody
	public String updateInfo(MultipartFile[] headerFile, BlogVO vo) throws IllegalStateException, IOException {
		BlogVO result = blogService.updateInfo(headerFile, vo);
		if(result != null) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 카테고리 등록
	@RequestMapping(value = "/cateInsert", method=RequestMethod.POST)
	@ResponseBody
	public CategoriesVO cateInsert(CategoriesVO cateVO) {
		blogService.cateInsert(cateVO);
		return cateVO;
	}
	
	// 카테고리 삭제
	@RequestMapping(value = "/cateDelete", method=RequestMethod.POST)
	@ResponseBody
	public String cateDelete(String categoryId) {
		blogService.cateDelete(categoryId);
		return "success";
	}
	
}
