package com.onsaem.web.chal.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class RepliesVO {
	String replyId;
	String content;
	String groupId;
	String groups;
	String writerId;
	Date replyDate;
	String publicScope;
}
