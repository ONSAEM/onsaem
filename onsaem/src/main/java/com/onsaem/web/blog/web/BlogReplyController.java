package com.onsaem.web.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.onsaem.web.blog.service.BlogReplyService;

@Controller
@CrossOrigin(origins = "*")
public class BlogReplyController {
	@Autowired
	BlogReplyService replyService;
}
