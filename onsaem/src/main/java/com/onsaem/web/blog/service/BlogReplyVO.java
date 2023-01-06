package com.onsaem.web.blog.service;

import java.util.Date;

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
	Date replyDate;
	String publicScope;
}
