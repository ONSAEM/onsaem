package com.onsaem.web.chal.service;

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
}
