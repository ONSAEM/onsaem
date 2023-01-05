package com.onsaem.web.chal.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ParticipantVO {
	String paricipateId; //참가코드
	String chalId; //챌린저스코드
	Integer privateDonate; //기부금 - 개인이 내는돈, 
	Integer betPoint; //내기포인트 
	String participantId; //참가회원id
	String result; //결과 - 승 / 패
	Integer resultPoint; //결과로 받는 포인트 or 잃는 포인트
}
