package com.onsaem.web.blog.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class BlogReplyVO {
	String replyId;
	String content;
	String groupId;
	String groups;
	String writerId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date replyDate;
	String publicScope;
}
