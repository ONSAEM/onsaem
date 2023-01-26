package com.onsaem.web.common.service;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewVO {
	String reviewId;
	String reviewContent;
	double reviewStar;
	String writerId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date reviewDate;
	String groupId;
	String groups;
	
	//쇼핑몰리뷰
	String productName;
	String fileRoute;
}
