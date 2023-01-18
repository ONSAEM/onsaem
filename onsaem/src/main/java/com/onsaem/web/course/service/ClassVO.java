package com.onsaem.web.course.service;

import java.sql.Date;

import lombok.Data;
@Data
public class ClassVO {
	String cNo;
	String classId;
	Date startDate;
	Date endDate;
	String totalPeople ;
	String status;	
}
