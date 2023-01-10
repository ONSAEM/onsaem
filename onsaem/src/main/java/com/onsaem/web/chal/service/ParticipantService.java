package com.onsaem.web.chal.service;

import java.util.List;

import com.onsaem.web.common.service.PaymentVO;

public interface ParticipantService {
	//참가자 조회 - 챌린저스 한개에 대한 
		List<ParticipantVO> listParticipantAll(String chalId);
		
		//한 참가자가 참여하는 챌린저스 목록,
		List<ParticipantVO> listChalForOne(ParticipantVO vo);
		
		//참가자 상세 조회
		ParticipantVO getParticipant(ParticipantVO vo);
		
		//챌린저스 참가
		Integer inputParticipant(ParticipantVO vo);
		
		//챌린저스 참가 취소
		Integer delParticipant(String ParticipantId);
		
		//참가 시 결제
		Integer inputPayment(PaymentVO vo);
		
		//참가 취소 시 결제테이블에서 삭제
		Integer delPayment();
}
