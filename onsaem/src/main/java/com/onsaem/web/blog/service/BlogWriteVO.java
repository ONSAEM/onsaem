package com.onsaem.web.blog.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class BlogWriteVO {
	String writeId;
	String blogId;
	String writeTitle;
	String subtitle;
	String categoryId;
	String publicScope;
	String blogWrite;
	String writerNickname;
	int cnt;
	int likeNumber;
	Date writeDate;
}
