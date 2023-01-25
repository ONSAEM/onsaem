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

import com.onsaem.web.blog.service.BlogReplyService;
import com.onsaem.web.common.service.RepliesVO;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/blog")
public class BlogReplyController {
	@Autowired
	BlogReplyService replyService;
	
	// 댓글 등록 처리(등록)
	@RequestMapping(value = "/myblog/blogWrite/replyInsert", method = RequestMethod.POST)
	@ResponseBody  //ajax 응답은 responseBody
	public RepliesVO replyInsert(Model model, RepliesVO vo, Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String id = userDetails.getUsername();
		vo.setWriterId(id);
		replyService.replyInsert(vo);
		return vo;
	}
}
