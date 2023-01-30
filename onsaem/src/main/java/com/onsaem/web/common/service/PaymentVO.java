package com.onsaem.web.common.service;

import java.util.Date;

import com.onsaem.web.chat.service.ChatRoomVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentVO {
	private String paymentId;
	private Integer price;
	private String payerId;
	private String paymentMethod;
	private Date payDate;
	private String groupId;
	private String groups;
	private String status;
	private int usePoint;
	
	//챌린저스에서 사용
	private Integer donationFee;
	private Integer betPoint;
}
