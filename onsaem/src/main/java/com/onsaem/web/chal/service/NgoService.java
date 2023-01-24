package com.onsaem.web.chal.service;

import java.util.List;

public interface NgoService {
	
		//ngo등록-> 이용자입장
		Integer inputNgo(NgoVO vo);
		
		//ngo승인,반려,,등등 -> update기능,
		Integer updateNgo(NgoVO vo);
		
		//신청목록 조회 - 전체(condition조건 붙여야한다)
		List<NgoVO> listNgo(String condition);
		
		//신청목록 조회 - 항시 병시별 조회
		List<NgoVO> listNgoClass(String classes);
		
		//한건 상세조회
		NgoVO getNgo(String ngoId);
		
		//안쓸거같긴함 - ngo삭제 - 
		Integer delNgo(String ngoId);
		
		//내가 신청한 ngo목록들,,
		List<NgoVO> myApplies(String writerId);
		
		//ngo 상태 업데이트 - 반려/승인용
		Integer updateCondition(NgoVO vo);
		
		//반려
		Integer rejectNgo(NgoVO vo);
		
		//승인된 목록 이외의 ngo리스트
		List<NgoVO> notApproveList();
}
