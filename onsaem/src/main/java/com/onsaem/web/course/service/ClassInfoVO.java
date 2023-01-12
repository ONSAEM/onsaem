package com.onsaem.web.course.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassInfoVO {
	
	// 강의 정보
	String classId;
	String className;
	int price;
	int classTime;
	String explanation;
	String status;
	String classGroup;
	String memberId;
	Date classDate;
	String returnReason;
	String difficulty;
	String classAddr;
	double star;
	int  reviewCount;
	
	// 강의 이미지
	String fileName;
	String fileRoute;
	String mediaName;
	
	//검색
	int maxPrice;
	int minPrice;
	Date startdate;
	Date enddate;
	
	//페이징
	Integer first = 1;
	Integer last = 9;

	
}
