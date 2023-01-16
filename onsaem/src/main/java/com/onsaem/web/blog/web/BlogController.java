package com.onsaem.web.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onsaem.web.blog.service.BlogService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	BlogService blogService;
	
	// 블로그 관리 페이지로 이동
	@RequestMapping(value="/myblog/blogTask", method=RequestMethod.GET)
	public String task(Model model, String blogId) {
		model.addAttribute("blogTask", blogService.task(blogId));
		System.out.println(model.getAttribute("blogTask"));
		return "content/blog/blogTask";
	}
	
	
}
