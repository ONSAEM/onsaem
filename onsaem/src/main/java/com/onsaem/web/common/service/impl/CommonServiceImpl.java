package com.onsaem.web.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.common.mapper.CommonMapper;
import com.onsaem.web.common.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	CommonMapper commonMapper;
	
	@Override
	public List<Map<String, String>> getBankList() {

		return commonMapper.getBankList();
	}
}
