package com.onsaem.web.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.blog.service.MomentService;
import com.onsaem.web.blog.service.MomentsVO;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/blog")
public class MomentController {
	@Autowired
	MomentService momentService;
	
	// 모먼트 상세보기
	@RequestMapping(value="/getMoment", method= RequestMethod.POST)
	@ResponseBody
	public MomentsVO getMoment(String momentId) {
		MomentsVO vo = momentService.getMoment(momentId);
		return vo;
	}
}
