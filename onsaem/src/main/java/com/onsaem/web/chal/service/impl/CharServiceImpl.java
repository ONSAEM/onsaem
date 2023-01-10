package com.onsaem.web.chal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.chal.mapper.ChalMapper;
import com.onsaem.web.chal.mapper.ParticipantMapper;
import com.onsaem.web.chal.mapper.ProofMapper;
import com.onsaem.web.chal.service.ChalService;
import com.onsaem.web.chal.service.ChalVO;
import com.onsaem.web.chal.service.ParticipantVO;
import com.onsaem.web.common.service.MediaVO;

@Component
public class CharServiceImpl implements ChalService {
	@Autowired ChalMapper chalMapper;
	@Autowired ProofMapper proofMapper;
	@Autowired ParticipantMapper partMapper;

	@Override
	public List<ChalVO> getChalAll() {
		// TODO Auto-generated method stub
		return chalMapper.getChalAll();
	}

	@Override
	public List<ChalVO> getChalNgoAll(String ngoName) {
		// TODO Auto-generated method stub
		return chalMapper.getChalNgoAll(ngoName);
	}

	@Override
	public List<ChalVO> getChalTeamAll(String value) {
		// TODO Auto-generated method stub
		return chalMapper.getChalTeamAll(value);
	}

	@Override
	public Integer inputChal(ChalVO vo) {
		// TODO Auto-generated method stub
		chalMapper.inputChal(vo);
	
		return  0;
	}

	@Override
	public Integer modifyChal(ChalVO vo) {
		// TODO Auto-generated method stub
		return chalMapper.modifyChal(vo);
	}

	@Override
	public Integer updateDonate(Integer money) {
		// TODO Auto-generated method stub
		return chalMapper.updateDonate(money);
	}

	@Override
	public Integer delChal(String chalId) {
		// TODO Auto-generated method stub
		return chalMapper.delChal(chalId);
	}

	@Override
	public ChalVO getChal(String chalId) {
		// TODO Auto-generated method stub
		return chalMapper.getChal(chalId);
	}

}
