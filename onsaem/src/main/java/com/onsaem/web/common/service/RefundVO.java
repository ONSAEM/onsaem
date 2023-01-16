package com.onsaem.web.common.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class RefundVO {
	String refundId;
	String paymentId;
	String refundMethod;
	String refundAccount;
	Date refundDate;
	String groupId;
	String groups;
}
