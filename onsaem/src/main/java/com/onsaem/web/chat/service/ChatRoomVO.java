package com.onsaem.web.chat.service;


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
