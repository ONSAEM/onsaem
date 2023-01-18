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
		return ngoMapper.inputNgo(vo);
	}

	@Override
	public Integer updateNgo(NgoVO vo) {
		// TODO Auto-generated method stub
		return ngoMapper.updateNgo(vo);
	}

	@Override
	public List<NgoVO> listNgo(String condition) {
		// TODO Auto-generated method stub
		return ngoMapper.listNgo(condition);
	}

	@Override
	public List<NgoVO> listNgoClass(String classes) {
		// TODO Auto-generated method stub
		return ngoMapper.listNgoClass(classes);
	}

	@Override
	public NgoVO getNgo(String ngoId) {
		// TODO Auto-generated method stub
		return ngoMapper.getNgo(ngoId);
	}

	@Override
	public Integer delNgo(String ngoId) {
		// TODO Auto-generated method stub
		return ngoMapper.delNgo(ngoId);
	}

	@Override
	public List<NgoVO> myApplies(String writerId) {
		// TODO Auto-generated method stub
		return ngoMapper.myApplies(writerId);
	}
	
	
	

}
