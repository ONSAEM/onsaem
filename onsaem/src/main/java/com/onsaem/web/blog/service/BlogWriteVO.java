package com.onsaem.web.blog.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class BlogWriteVO {
	String writeId;
	String blogId;
	String writeTitle;
	String subTitle;
	String categoryId;
	String publicScope;
	String blogWrite;
	String writerNickname;
	int cnt;
	int likeNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date writeDate;
}
