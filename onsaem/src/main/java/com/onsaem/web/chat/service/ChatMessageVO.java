package com.onsaem.web.chat.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ChatMessageVO {
	String msgId;
	String roomId;
	String writeContent;
	Date writeDate;
	String memberId;
}
