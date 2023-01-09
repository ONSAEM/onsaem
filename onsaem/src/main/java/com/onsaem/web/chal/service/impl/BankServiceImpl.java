package com.onsaem.web.chal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.chal.mapper.BankMapper;
import com.onsaem.web.chal.service.BankService;
import com.onsaem.web.chal.service.BankVO;

@Component
public class BankServiceImpl implements BankService {
	
	@Autowired BankMapper bankMapper;
	
	@Override
	public List<BankVO> listBank() {
		// TODO Auto-generated method stub
		return bankMapper.listBank();
	}

}
