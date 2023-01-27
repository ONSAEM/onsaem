package com.onsaem.web.common.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class QuestionVO {
	private String queId;
	private String queContent;
	private String writerId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH24:MI:SS")
	private Date queDate;
	private String ansContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH24:MI:SS")
	private Date ansDate;
	private String groupId;
	private String groups;

	// 페이징
	private Integer first = 1;
	private Integer last = 9;
}
