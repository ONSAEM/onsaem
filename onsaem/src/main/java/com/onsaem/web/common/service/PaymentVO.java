package com.onsaem.web.common.service;

import java.util.Date;

import com.onsaem.web.chat.service.ChatRoomVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentVO {
	String paymentId;
	Integer price;
	String payerId;
	String paymentMethod;
	Date payDate;
	String groupId;
	String groups;
	String status;
	
	//챌린저스에서 사용
	Integer donationFee;
	Integer betPoint;
}
