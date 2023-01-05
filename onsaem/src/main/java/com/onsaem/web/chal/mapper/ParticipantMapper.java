package com.onsaem.web.chal.mapper;

import java.util.List;

import com.onsaem.web.chal.service.ParticipantVO;
import com.onsaem.web.chal.service.PaymentVO;

public interface ParticipantMapper {
	
	//챌린저스 참가
	Integer inputParticipant(ParticipantVO vo);
	
	//챌린저스 참가 취소
	Integer delParticipant(String ParticipantId);
	
	//참가 시 결제
	Integer inputPayment(PaymentVO vo);
	
	//참가 취소 시 결제테이블에서 삭제
	Integer delPayment();
	
	//참가자 조회
	List<ParticipantVO> listParticipantAll(String chalId);
	
	//참가자 상세 조회
	ParticipantVO getParticipant(ParticipantVO vo);
}
