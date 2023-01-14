package com.onsaem.web.member.web;

import org.apache.tomcat.util.modeler.modules.ModelerSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onsaem.web.member.service.MemberService;


@Controller
@CrossOrigin(origins = "*")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	// 로그인페이지 이동
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginPage(String excep, Model model) {
		model.addAttribute("excep", excep);
		return "content/member/loginForm";
	}

}
