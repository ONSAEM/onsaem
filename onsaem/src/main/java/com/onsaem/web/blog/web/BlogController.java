package com.onsaem.web.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.blog.service.BlogService;
import com.onsaem.web.common.service.LikeVO;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	BlogService blogService;
	
	// 블로그 관리 페이지로 이동
	@RequestMapping(value="/myblog/blogTask", method=RequestMethod.GET)
	public String getBlogInfo(Model model, String blogId) {
		model.addAttribute("blogInfos", blogService.getBlogInfo(blogId));
		System.out.println(model.getAttribute("blogInfos"));
		return "content/blog/blogTask";
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
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String id = userDetails.getUsername();		
		vo.setMemberId(id);
		blogService.delSub(vo);
		return vo;
	}
}
