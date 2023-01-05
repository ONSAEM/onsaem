package com.onsaem.web.chal.service;

import java.util.Date;

import com.onsaem.web.chat.service.ChatRoomVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentVO {
	String payment_id;
	Integer price;
	String payer_id;
	String payment_method;
	Date pay_date;
	String group_id;
	String groups;
}
