package com.onsaem.web.member.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.common.service.CommonService;
import com.onsaem.web.member.service.MemberService;
import com.onsaem.web.member.service.MemberVO;

@Controller
@CrossOrigin(origins = "*")
public class MemberController {

	@Autowired
	MemberService memberService;

	@Autowired
	CommonService commonService;

	// 로그인페이지 이동
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginPage(String excep, Model model) {
		model.addAttribute("excep", excep);
		return "content/member/loginForm";
	}

	// 회원가입페이지 이동
	@RequestMapping(value = "/SignUpPage", method = RequestMethod.GET)
	public String SignUpPage(Model model) {
		model.addAttribute("bankList", commonService.getBankList());
		return "content/member/signUpForm";
	}

	// 회원단건조회
	@RequestMapping(value = "/getMember", method = RequestMethod.POST)
	@ResponseBody
	public MemberVO getMember(String memberId) {
		return memberService.getMember(memberId);
	}

	// 아이디 체크
	@RequestMapping(value = "/checkId", method = RequestMethod.GET)
	@ResponseBody
	public MemberVO checkId(String memberId) {
		return memberService.checkId(memberId);
	}

	// 이메일 인증
	@RequestMapping(value = "/authMail", method = RequestMethod.POST)
	@ResponseBody
	public String authMail(String email) {
		return memberService.sendAuthMail(email);
	}

	// 회원등록
	@RequestMapping(value = "/insertMember", method = RequestMethod.POST)
	@ResponseBody
	public MemberVO insertMember(MultipartFile[] profileFile, MemberVO member) throws IllegalStateException, IOException {
		memberService.insertMember(profileFile, member);
		return member;
	}
}
