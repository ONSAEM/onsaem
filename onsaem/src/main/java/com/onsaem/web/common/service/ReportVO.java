package com.onsaem.web.common.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ReportVO {
	
	//신고
	String reportId;
	String fromId;
	String toId;
	Date reportDate;
	String reason;
	String detailReason;
	String status;
	String groups;
	String groupId;
	
	String rn;
}
	