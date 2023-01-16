package com.onsaem.web.chal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.chal.mapper.ParticipantMapper;
import com.onsaem.web.chal.service.ParticipantService;
import com.onsaem.web.chal.service.ParticipantVO;
import com.onsaem.web.common.service.PaymentVO;
import com.onsaem.web.common.service.RefundVO;

@Component
public class ParticipantServiceImpl implements ParticipantService {
	@Autowired ParticipantMapper mapper;

	@Override
	public Integer inputParticipant(ParticipantVO vo) {
		// TODO Auto-generated method stub
		return mapper.inputParticipant(vo);
	}

	@Override
	public Integer delParticipant(String ParticipantId) {
		// TODO Auto-generated method stub
		return mapper.delParticipant(ParticipantId);
	}

	@Override
	public Integer inputPayment(PaymentVO vo) {
		// TODO Auto-generated method stub
		return mapper.inputPayment(vo);
	}

	@Override
	public Integer delPayment() {
		// TODO Auto-generated method stub
		return mapper.delPayment();
	}

	@Override
	public List<ParticipantVO> listParticipantAll(String chalId) {
		// TODO Auto-generated method stub
		return mapper.listParticipantAll(chalId);
	}

	@Override
	public ParticipantVO getParticipant(ParticipantVO vo) {
		// TODO Auto-generated method stub
		return mapper.getParticipant(vo);
	}


	@Override
	public Integer inputRefund(RefundVO vo) {
		// TODO Auto-generated method stub
		return mapper.inputRefund(vo);
	}

	@Override
	public ParticipantVO cntParticipant(ParticipantVO vo) {
		// TODO Auto-generated method stub
		return mapper.cntParticipant(vo);
	}

}
