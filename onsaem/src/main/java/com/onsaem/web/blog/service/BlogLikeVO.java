package com.onsaem.web.blog.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class BlogLikeVO {
	String likeId;
	String memberId;
	String groupId;
	String groups;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date insertDate;
}
