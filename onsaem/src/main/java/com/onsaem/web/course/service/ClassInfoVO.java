package com.onsaem.web.course.service;

import java.sql.Date;

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
	double star;
	int  reviewCount;
	
	// 강의 이미지
	String fileName;
	String fileRoute;
	String mediaName;
	
	//검색 가격대
	int maxPrice;
	int minPrice;
	
	//페이징
	Integer first = 1;
	Integer last = 9;

	
}
