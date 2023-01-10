package com.onsaem.web.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.member.mapper.LoginMapper;
import com.onsaem.web.member.service.LoginService;
import com.onsaem.web.member.service.MemberVO;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired LoginMapper loginMapper;
	@Override
	public MemberVO memberLogin(MemberVO vo) {
		// 로그인
		return loginMapper.memberLogin(vo);
	}
	
}
