package com.onsaem.web.common.service;

import java.util.Date;

import com.onsaem.web.course.service.ClassInfoVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class LikeVO {
	private String likeId;
	private String memberId;
	private String groupId;
	private String groups;
	private Date insertDate;
	private String count;
	
	
	private Integer cnt;
	private String likeCk;
	
	private String fileRoute;
	
	private ClassInfoVO classInfo;
}
