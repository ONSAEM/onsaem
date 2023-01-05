package com.onsaem.web.donation.service;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DonationHIstoriesVO {
	String donationId;
	String projectId;
	Date donationDate;
	String memberId;
}
