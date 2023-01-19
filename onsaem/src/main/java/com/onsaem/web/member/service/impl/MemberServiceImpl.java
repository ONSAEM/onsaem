package com.onsaem.web.member.service.impl;


import java.util.Random;

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
		dto.setSubject("[온샘] 이메일 인증번호 발급");
		dto.setTitle("회원가입 이메일 인증");
		dto.setFrom("onseam");
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	                                   .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	                                   .limit(targetStringLength)
	                                   .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	                                   .toString();
		dto.setContent("인증번호 : "+generatedString);

		if(mailService.sendMail(dto,null)) {
			return generatedString;
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
