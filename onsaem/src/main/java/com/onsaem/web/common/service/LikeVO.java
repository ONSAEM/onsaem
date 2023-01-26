package com.onsaem.web.common.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class LikeVO {
	String likeId;
	String memberId;
	String groupId;
	String groups;
	Date insertDate;
	String count;
	
	Integer cnt;
	String likeCk;
}
