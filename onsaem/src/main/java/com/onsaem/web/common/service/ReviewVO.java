package com.onsaem.web.common.service;

import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewVO {
	private String reviewId;
	private String reviewContent;
	private double reviewStar;
	private String writerId;
	private String nickname;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH24:MI:SS")
	private Date reviewDate;
	private String groupId;
	private String groups;
	
	//쇼핑몰리뷰
	String productName;
	String fileRoute;
	
	private MediaVO profile;
	private MediaVO reviewMedia;
	private List<MediaVO> reviewMediaList;
	private String orderId = "OD0";
	// 페이징
	private Integer first = 1;
	private Integer last = 9;
}
