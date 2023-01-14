package com.onsaem.web.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onsaem.web.member.mapper.MemberMapper;
import com.onsaem.web.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService, UserDetailsService {
	
	@Autowired MemberMapper loginMapper;

	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		
		return loginMapper.getMember(memberId);
	}
}
