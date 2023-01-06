package com.onsaem.web.blog.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class BlogLikeVO {
	String likeId;
	String memberId;
	String groupId;
	String groups;
	Date insertDate;
}
