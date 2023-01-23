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

	// 이메일 체크
	@RequestMapping(value = "/searchEmail", method = RequestMethod.GET)
	@ResponseBody
	public String searchEmail(String email) {
		return memberService.searchEmail(email);
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
	public String insertMember(MultipartFile[] profileFile, MemberVO member) throws IllegalStateException, IOException {
		return memberService.insertMember(profileFile, member);
	}

	// 회원가입완료페이지 이동
	@RequestMapping(value = "/signUpcompleted", method = RequestMethod.GET)
	public String signUpcompleted(String email) {
		return "content/member/signUpcompleted";
	}

	// 아이디 보내기
	@RequestMapping(value = "/sendIdMail", method = RequestMethod.POST)
	@ResponseBody
	public String sendIdMail(String email) {
		return memberService.sendIdMail(email);
	}

	// 비밀번호 변경
	@RequestMapping(value = "/updatePw", method = RequestMethod.POST)
	@ResponseBody
	public String updatePw(MemberVO member) {
		return memberService.updatePw(member);
	}

	// 회원정보 변경
	@RequestMapping(value = "/updateMember", method = RequestMethod.POST)
	@ResponseBody
	public String updateMember(MultipartFile[] profileFile, MemberVO member) throws IllegalStateException, IOException {
		return memberService.updateMember(profileFile, member);
	}

	// 마이페이지 메인페이지로 이동
	@RequestMapping(value = "/myPageMain", method = RequestMethod.GET)
	public String myPageMain(String email) {
		return "content/member/myPageMain";
	}

	// 마이페이지 회원정보페이지로 이동
	@RequestMapping(value = "/myInfo", method = RequestMethod.GET)
	public String myInfoPage(String email) {
		return "content/member/myInfo";
	}

	// 마이페이지 정보수정페이지로 이동
	@RequestMapping(value = "/myinfoModify", method = RequestMethod.GET)
	public String myinfoModify(String email, Model model) {
		model.addAttribute("bankList", commonService.getBankList());
		return "content/member/myinfoModify";
	}
}
