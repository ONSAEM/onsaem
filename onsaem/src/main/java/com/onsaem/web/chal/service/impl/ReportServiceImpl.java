package com.onsaem.web.chal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.chal.mapper.ReportMapper;
import com.onsaem.web.chal.service.ReportService;
import com.onsaem.web.common.service.ReportVO;

@Component
public class ReportServiceImpl implements ReportService {

	@Autowired ReportMapper reportMapper;
	
	@Override
	public Integer inputReport(ReportVO vo) {
		// TODO Auto-generated method stub
		return reportMapper.inputReport(vo);
	}

	@Override
	public Integer updateReport(ReportVO vo) {
		// TODO Auto-generated method stub
		return reportMapper.updateReport(vo);
	}

	@Override
	public List<ReportVO> listReportAll(ReportVO vo) {
		// TODO Auto-generated method stub
		return reportMapper.listReportAll(vo);
	}

	@Override
	public ReportVO getReport(ReportVO vo) {
		// TODO Auto-generated method stub
		return reportMapper.getReport(vo);
	}

}
