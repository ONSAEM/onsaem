package com.onsaem.web.member.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onsaem.web.common.service.MailService;
import com.onsaem.web.common.service.MailDTO;
import com.onsaem.web.member.mapper.MemberMapper;
import com.onsaem.web.member.service.MemberService;
import com.onsaem.web.member.service.MemberVO;

@Service
public class MemberServiceImpl implements MemberService, UserDetailsService {
	
	@Autowired MemberMapper memberMapper; 
	
	@Autowired MailService mailService;
	
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		
		return memberMapper.getMember(memberId);
	}

	@Override
	public MemberVO checkId(String memberId) {

		return memberMapper.checkId(memberId);
	}

	@Override
	public String sendAuthMail(String email) {
		MailDTO dto = new MailDTO();
		String[] emList = new String[1];
		emList[0]=email;
		dto.setAddress(emList);
		dto.setSubject("이메일 인증번호 발급");
		dto.setTitle("이메일 인증");
		dto.setFrom("onseam");
		dto.setContent("호경바보");
		dto.setTemplate("mail");

		if(mailService.sendMail(dto,null)) {
			return "success";
		}else {
			return "fail";
		}
	}

	@Override
	public MemberVO getMember(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}
}
