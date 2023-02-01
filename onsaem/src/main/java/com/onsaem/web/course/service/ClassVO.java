package com.onsaem.web.course.service;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.onsaem.web.common.service.MediaVO;

import lombok.Data;
@Data
public class ClassVO {
	private String cNo;
	private String classId;
	private String classDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH24:MI:SS")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH24:MI:SS")
	private Date endDate;
	private String startTime;
	private String endTime;
	private String totalPeople;
	private String status;
	private String bookingPeople;
	
	private ClassInfoVO classInfo;
	private MediaVO classMedia;
}
