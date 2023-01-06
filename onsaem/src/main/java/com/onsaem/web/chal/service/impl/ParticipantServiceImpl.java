package com.onsaem.web.chal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.chal.mapper.ParticipantMapper;
import com.onsaem.web.chal.service.ParticipantService;
import com.onsaem.web.chal.service.ParticipantVO;
import com.onsaem.web.chal.service.PaymentVO;

@Component
public class ParticipantServiceImpl implements ParticipantService {
	@Autowired ParticipantMapper mapper;

	@Override
	public Integer inputParticipant(ParticipantVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delParticipant(String ParticipantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer inputPayment(PaymentVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delPayment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParticipantVO> listParticipantAll(String chalId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParticipantVO getParticipant(ParticipantVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParticipantVO> listChalForOne(ParticipantVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
