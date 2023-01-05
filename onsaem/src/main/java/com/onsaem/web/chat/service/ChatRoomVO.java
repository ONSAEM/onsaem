package com.onsaem.web.chat.service;

import java.util.Date;

import com.onsaem.web.chal.service.ProofVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ChatRoomVO {
	String roomId;
	String roomName;
	String classId;
	String classes;
}
