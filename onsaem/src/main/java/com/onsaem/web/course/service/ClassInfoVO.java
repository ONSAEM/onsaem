package com.onsaem.web.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.onsaem.web.common.service.MediaVO;

import lombok.Data;

@Data
public class ClassInfoVO {
	
	// 강의 정보
	private String classId;
	private String className;
	private int price;
	private int classTime;
	private String explanation;
	private String status;
	private String memberId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH24:MI:SS")
	private Date classDate;
	private String returnReason;
	private String difficulty;
	private String classAddr;
	private double star;
	private int reviewCount;
	private int queCount;
	private String addr;
	private String totalPeople;
	
	// 강의 이미지
	private MediaVO media;
	private List<MediaVO> mediaList;
	
	//검색
	private int maxPrice;
	private int minPrice;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	private String orderId = "OD0";
	
	//페이징
	private Integer first = 1;
	private Integer last = 9;

	
}
