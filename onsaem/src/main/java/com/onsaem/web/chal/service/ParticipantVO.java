package com.onsaem.web.chal.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ParticipantVO {
	String participateId; //참가코드
	String chalId; //챌린저스코드
	Integer privateDonate; //기부금 - 개인이 내는돈, 
	Integer betPoint; //내기포인트 
	String participantId; //참가회원id
	String result; //결과 - 승 / 패
	Integer resultPoint; //결과로 받는 포인트 or 잃는 포인트
	
	
	//조인할 떄 위해서 만든변수들
	String chalName;
	String classes; //시작전, 진행중, 완료 
	String subClass; //팀전,개인전 여부
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date endDate;
	String frequency;
}
