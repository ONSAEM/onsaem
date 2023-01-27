package com.onsaem.web.chal.service;

import java.util.List;

import com.onsaem.web.common.service.PaymentVO;
import com.onsaem.web.common.service.RefundVO;

public interface ParticipantService {
	//참가자 조회 - 챌린저스 한개에 대한 
		List<ParticipantVO> listParticipantAll(ParticipantVO vo);
		
		
		//참가자 상세 조회
		ParticipantVO getParticipant(ParticipantVO vo);
		
		//참가여부확인
		ParticipantVO cntParticipant(ParticipantVO vo);
		
		//챌린저스 참가
		Integer inputParticipant(ParticipantVO vo);
		
		//챌린저스 참가 취소
		Integer delParticipant(ParticipantVO vo);
		
		//참가 시 결제
		Integer inputPayment(PaymentVO vo);
				
		//환불테이블에 등록
		Integer inputRefund(RefundVO vo);
		
		//참가 취소 시 결제테이블 수정
		Integer updateForRefund(PaymentVO vo);
		
		//참가자 테이블 result_point 업데이트
		Integer updateResultPoint(ParticipantVO vo);
}
