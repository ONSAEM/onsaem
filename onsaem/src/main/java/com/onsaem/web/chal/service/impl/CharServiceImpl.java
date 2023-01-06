package com.onsaem.web.chal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.chal.mapper.ChalMapper;
import com.onsaem.web.chal.service.ChalService;
import com.onsaem.web.chal.service.ChalVO;

@Component
public class CharServiceImpl implements ChalService {
	@Autowired ChalMapper chalMapper;

	@Override
	public List<ChalVO> getChalAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChalVO> getChalNgoAll(String ngoName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChalVO> getChalTeamAll(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer inputChal(ChalVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer modifyChal(ChalVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateDonate(Integer money) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delChal(String chalId) {
		// TODO Auto-generated method stub
		return null;
	}

}
