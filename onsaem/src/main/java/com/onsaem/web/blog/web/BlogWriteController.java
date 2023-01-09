package com.onsaem.web.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.blog.service.BlogWriteService;
import com.onsaem.web.blog.service.BlogWriteVO;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/blog")
public class BlogWriteController {
	@Autowired
	BlogWriteService blogWriteService;
	
	// 블로그 메인으로 이동 (조회)
	@RequestMapping(value = "/blogMain", method = RequestMethod.GET)
	public String blogWriteList(Model model) {
		model.addAttribute("blogList", blogWriteService.getBlogList(null));
		return "content/blog/blogMain";
	}
	
	// 내 블로그로 이동
	@RequestMapping(value = "/myblog/{userId}", method = RequestMethod.GET)
	public String myblog(Model model, @PathVariable String userId) {
		model.addAttribute("myblog", blogWriteService.myBlog(userId));
		return "content/blog/myblog";
	}
	
	// 블로그 글 상세 페이지로 이동(단건조회)
	@RequestMapping(value = "/myblog/blogWrite/{userId}/{bno}", method = RequestMethod.GET)
	public String blogWrite(Model model, @PathVariable String userId, @PathVariable String bno) {
		model.addAttribute("blogWrite", blogWriteService.getBlog(userId, bno));
		return "content/blog/blogWrite";
	}
	
	// 블로그 글 등록 페이지로 이동
	@RequestMapping(value = "/myblog/blogWrite/blogInsert", method = RequestMethod.GET)
	public String blogInsertPage(Model model) {
		//model.addAttribute("blogInsert", blogWriteService.blogInsert(null));
		return "content/blog/blogInsert";
	}
	
	// 블로그 글 등록 처리(등록)
	@RequestMapping(value = "/myblog/blogWrite/blogInsert", method = RequestMethod.POST)
	@ResponseBody  //ajax 응답은 responseBody
	public BlogWriteVO blogInsert(Model model, BlogWriteVO vo) {
		vo.setBlogId("test000");
		model.addAttribute("blogInsert", blogWriteService.blogInsert(vo));
		return vo;
	}
	// 블로그 글 수정 페이지로 이동
	
	// 블로그 글 수정 처리(수정)
	
	// 블로그 글 삭제 처리(삭제)
	
}
