package com.onsaem.web.member.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.common.service.MediaVO;
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
	
	// 회원가입페이지 이동
	@RequestMapping(value = "/SignUpPage", method = RequestMethod.GET)
	public String SignUpPage() {
		return "content/member/signUpForm";
	}
		
	// 이메일 인증
	@RequestMapping(value = "/authMail", method = RequestMethod.POST)
	@ResponseBody
	public String authMail(@RequestParam(value = "fileList", required = false) List<MediaVO> fileList, String email) {
		
		return memberService.sendAuthMail(email, fileList);
	}
}
