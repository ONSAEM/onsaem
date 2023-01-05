package com.onsaem.web.donation.service;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DonationProjectsVO {
	String projectId;
	String projectName;
	String condition;
	String content;
	String plan;
	String receipt;
	String ngoId;
	Date startDate;
	Date endDate;
}
