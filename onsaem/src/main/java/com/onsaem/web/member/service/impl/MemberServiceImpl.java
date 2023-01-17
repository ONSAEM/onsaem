package com.onsaem.web.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.common.service.MailService;
import com.onsaem.web.common.service.MailDTO;
import com.onsaem.web.common.service.MediaVO;
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
	public String sendAuthMail(String email, List<MediaVO> fileList) {
		MailDTO vo = new MailDTO();
//		vo.setSubject("이메일 체크");
//		vo.setContent("<h1>이메일</h1><br><p>이거 보내지면 성공</p>");
//		vo.setToEmail(email);
//		vo.setToName("호경쓰");
		
		if(mailService.sendMail(vo, fileList)) {
			return "success";
		}else {
			return "fail";
		}
	}
}
