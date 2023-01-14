package com.onsaem.web.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	public String loginPage() {
		return "content/member/loginForm";
	}

}
