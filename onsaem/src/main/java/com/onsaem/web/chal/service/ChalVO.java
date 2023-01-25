package com.onsaem.web.chal.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ChalVO {
	String chalId; //챌린저스 코드
	String classes; //챌린저스 분류 - 시작 전, 진행 중 , 완료
	String subClass; //분류 2 - 개인/단체
	String frequency; //인증 빈도
	String chalName;
	String ngoName; //기부처 코드?이름?
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date startDate; //시작일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date endDate; //종료일
	String contents; //상세설명
	String proofContent; //유의사항설명
	Integer donationFee; //기부금 - 총액
	String memberId; //글쓴이ID
	Integer teamFee; //팀전고정기부금 - 개인할당
	String receipt; //기부 영수증 있는지 없는지 유무
	Integer usercnt; //인원수 - 팀원 한정

	
	
	String fileRoute;
	String mediaName;
	String participantId;
	Integer privateDonate;
	String paymentId;
	String paymentMethod;
	Integer rownum;
	String result;
	String resultPoint;
	
	Integer first= 1;
	Integer last = 10;
	
}
