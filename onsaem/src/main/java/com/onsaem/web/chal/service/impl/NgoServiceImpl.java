package com.onsaem.web.chal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.chal.mapper.NgoMapper;
import com.onsaem.web.chal.service.NgoService;
import com.onsaem.web.chal.service.NgoVO;

@Component
public class NgoServiceImpl implements NgoService {
	@Autowired NgoMapper ngoMapper;

	@Override
	public Integer inputNgo(NgoVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateNgo(NgoVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NgoVO> listNgo(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NgoVO> listNgoClass(String classes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NgoVO getNgo(String ngoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delNgo(String ngoId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
