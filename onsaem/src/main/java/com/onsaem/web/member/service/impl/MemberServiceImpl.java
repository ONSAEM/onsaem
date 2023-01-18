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
	
	@Autowired MemberMapper loginMapper; 
	
	@Autowired MailService mailService;
	
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		
		return loginMapper.getMember(memberId);
	}

	@Override
	public MemberVO getMember(String memberId) {

		return loginMapper.getMember(memberId);
	}

	@Override
	public String sendAuthMail(String email) {
		MailDTO dto = new MailDTO();
		String[] emList = new String[1];
		emList[0]=email;
		dto.setAddress(emList);
		dto.setTitle("이메일 체크");
		dto.setFrom("onseam");
		dto.setContent("호경바보");
		dto.setTemplate("mailTest");

		if(mailService.sendMail(dto,null)) {
			return "success";
		}else {
			return "fail";
		}
	}
}
