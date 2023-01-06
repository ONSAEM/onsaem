package com.onsaem.web.blog.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class BlogReportVO {
	String reportId;
	String fromId;
	String toId;
	Date reportDate;
	String reason;
	String detailReason;
	String status;
	String groups;
	String groupId;
}
