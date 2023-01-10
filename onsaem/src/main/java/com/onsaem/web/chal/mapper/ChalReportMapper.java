package com.onsaem.web.chal.mapper;

import java.util.List;

import com.onsaem.web.common.service.ReportVO;

public interface ChalReportMapper {
	
	//신고등록
	Integer inputReport(ReportVO vo);
	
	//신고 수정 - 반려나, 승인 등등
	Integer updateReport(ReportVO vo);
	
	//신고목록 보기
	List<ReportVO> listReportAll(ReportVO vo);
	
	//신고한건
	ReportVO getReport(ReportVO vo);
}
