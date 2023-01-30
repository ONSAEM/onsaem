package com.onsaem.web.course.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.onsaem.web.common.service.MediaVO;

import lombok.Data;

@Data
public class BookingVO {
	private String bookingId;
	private String cNo;
	private String bookerName;
	private String bookerPhone;
	private String requirement;
	private int totalPeople;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH24:MI:SS")
	private Date orderDate;
	private String ordererId;
	private String paymentId;
	
	private MediaVO classMedia;

	// 페이징
	private Integer first = 1;
	private Integer last = 9;
}
