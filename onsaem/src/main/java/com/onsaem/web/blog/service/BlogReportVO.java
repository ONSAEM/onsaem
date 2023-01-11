package com.onsaem.web.blog.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class BlogReportVO {
	String reportId;
	String fromId;
	String toId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date reportDate;
	String reason;
	String detailReason;
	String status;
	String groups;
	String groupId;
}
