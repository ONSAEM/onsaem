package com.onsaem.web.chal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.chal.mapper.ChalMapper;
import com.onsaem.web.chal.mapper.ParticipantMapper;
import com.onsaem.web.chal.mapper.ProofMapper;
import com.onsaem.web.chal.service.ChalInfoVO;
import com.onsaem.web.chal.service.ChalService;
import com.onsaem.web.chal.service.ChalVO;
import com.onsaem.web.chal.service.ParticipantVO;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;

@Component
public class CharServiceImpl implements ChalService {
	@Autowired ChalMapper chalMapper;
	@Autowired ProofMapper proofMapper;
	@Autowired ParticipantMapper partMapper;

	@Override
	public List<ChalVO> getChalAll(ChalVO vo, Paging paging) {
		
		//paging.setTotalRecord(chalMapper.chalCount(vo));
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

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
	public Integer updateDonate(ChalVO vo) {
		// TODO Auto-generated method stub
		return chalMapper.updateDonate(vo);
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
	
	//마이페이지용 
	@Override
	public List<ChalVO> myCurentChal(ChalVO vo) {
		// TODO Auto-generated method stub
		return chalMapper.myCurentChal(vo);
	}

	@Override
	public List<ChalVO> myBeforeChal(ChalVO vo) {
		// TODO Auto-generated method stub
		return chalMapper.myBeforeChal(vo);
	}

	@Override
	public List<ChalVO> myEndChal(ChalVO vo) {
		// TODO Auto-generated method stub
		return chalMapper.myEndChal(vo);
	}

	@Override
	public List<ChalVO> donateRank() {
		// TODO Auto-generated method stub
		return chalMapper.donateRank();
	}

	@Override
	public List<ChalVO> currentChals() {
		// TODO Auto-generated method stub
		return chalMapper.currentChals();
	}

	@Override
	public List<ChalVO> beforeChals() {
		// TODO Auto-generated method stub
		return chalMapper.beforeChals();
	}

	@Override
	public List<ChalVO> endChals() {
		// TODO Auto-generated method stub
		return chalMapper.endChals();
	}

	@Override
	public Integer updateRecipt(ChalVO vo) {
		// TODO Auto-generated method stub
		return chalMapper.updateRecipt(vo);
	}

	@Override
	public MediaVO thumnail(String groupId) {
		// TODO Auto-generated method stub
		return chalMapper.thumnail(groupId);
	}

	@Override
	public MediaVO proofEx(String groupId) {
		// TODO Auto-generated method stub
		return chalMapper.proofEx(groupId);
	}

	@Override
	public List<ChalVO> AdminEndChals(String subClass) {
		// TODO Auto-generated method stub
		return chalMapper.AdminEndChals(subClass);
	}

}
