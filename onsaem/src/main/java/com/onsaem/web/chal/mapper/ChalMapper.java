package com.onsaem.web.chal.mapper;

import java.util.List;


import com.onsaem.web.chal.service.ChalVO;
import com.onsaem.web.chal.service.ParticipantVO;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;

public interface ChalMapper {
	
	//챌리저스 전체조회
	List<ChalVO> getChalAll(ChalVO vo);
	
	//챌린저스 갯수 조회
	int chalCount(ChalVO vo);
	
	//챌린저스 갯수 조회 - 시작전
	int cntYet(ChalVO vo);
	//챌린저스 갯수 조회 - 진행중 
	int cntNow(ChalVO vo);
	//챌린저스 갯수 조회 - 종료
	int cntEnd(ChalVO vo);
	
	//조건 전체조회- 기부처
	List<ChalVO> getChalNgoAll(ChalVO vo);
	
	//조건 전체조회 - 팀전/개인전
	List<ChalVO> getChalTeamAll(ChalVO vo);
	
	//Mypage - 일반회원의 진행중인 모든 챌린지
	List<ChalVO> myCurentChal(ChalVO vo);
		
	//Mypage - 일반회원의 시작전 챌린지모음
	List<ChalVO> myBeforeChal(ChalVO vo);
		
	//Mypage - 일반회원의 완료 챌린지 모음
	List<ChalVO> myEndChal(ChalVO vo);
	
	//상세조회
	ChalVO getChal(String chalId);
	
	//챌린저스 등록- 팀전/개인전 차이 두어야 한다 : 뷰페이지 2개 만들어야댐 
	Integer inputChal(ChalVO vo);
	
	//챌린저스 수정-개최자?
	Integer modifyChal(ChalVO vo);
	
	//챌린저스 기부금 update - 챌린저스 참가자 생길 때 마다
	Integer updateDonate(ChalVO vo);
	
	//챌린저스 취소-개최자만 가능함, 관리자랑 ㅋㅋ
	Integer delChal(String chalId);
	
	//기부금 3등까지
	List<ChalVO> donateRank();
	
	//챌린지 조회 - 진행중
	List<ChalVO> currentChals(ChalVO vo);
	//챌린지 조회 - 모집중
	List<ChalVO> beforeChals(ChalVO vo);
	// 챌린지 조회 - 완료
	List<ChalVO> endChals(ChalVO vo);
	
	//마이페이지 관리자용 영수증 업데이트
	Integer updateRecipt(ChalVO vo);
	
	//썸네일 가져오기
	MediaVO thumnail(String groupId);
	//인증예시 가져오기
	MediaVO proofEx(String groupId);
	
	//관리자용
	List<ChalVO> AdminEndChals(String subClass);
	
	//관리자용 member테이블 팀전 챌린저스 포인트 업데이트
	Integer updateTeamPoint(ParticipantVO vo);
		
	//관리자용 member테이블 개인전 챌린저스 포인트 업데이트
	Integer updateMemberPoint(ParticipantVO vo);
	
	//조만간 시작하는 챌린지
	List<ChalVO> ddayStartRank();
	
	
}	
