package com.onsaem.web.course.service;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassVO {
	String classId;
	String name;
	int price;
	int classTime;
	String explanation;
	String status;
	String classGroup;
	String memberId;
	String cNo;
	Date startDate;
	Date endDate;
	int totalPeople;
	String returnReason;
}
