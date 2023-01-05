package com.onsaem.web.chat.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ChatMemberVO {
	String chatMemberId;
	String roomId;
	String memberId;
}
