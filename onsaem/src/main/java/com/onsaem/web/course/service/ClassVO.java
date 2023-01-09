package com.onsaem.web.course.service;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassVO {
	String cNo;
	String classId;
	Date startDate;
	Date endDate;
	String totalPeople ;
	String status;	
}
