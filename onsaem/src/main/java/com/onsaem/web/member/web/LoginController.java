package com.onsaem.web.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.member.service.LoginService;
import com.onsaem.web.member.service.MemberVO;

@Controller
@CrossOrigin(origins = "*")
public class LoginController {
	@Autowired
	LoginService loginService;
	
	// 로그인 페이지로 이동
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String blogWriteList(Model model) {
		return "content/member/loginForm";
	}
	// 로그인 처리
	@RequestMapping(value = "/loginPage", method = RequestMethod.POST)
	   @ResponseBody
	   public String login(@RequestBody MemberVO vo, HttpServletRequest request) {
	      MemberVO vo2 = loginService.memberLogin(vo);
	      if(vo2!=null) {
	         HttpSession session = request.getSession();
	         session.setAttribute("member", vo2);
	         session.setAttribute("id", vo2.getMemberId());
	         session.setMaxInactiveInterval(1200);
	         return "O";       // 계정 존재  
	      }else {
	         return "X";	   // 계정 없음
	      }
	   }
	// 로그아웃 처리
	@RequestMapping(value = "/logoutPage", method = RequestMethod.GET)
		public String logout(HttpServletRequest request) {
		         HttpSession session = request.getSession();
		         session.invalidate();
		         return "content/main";
		   }
}
