package com.onsaem.web.blog.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MomentsVO {
	String momentId;
	String blogId;
	String addUrl;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date uploadStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date uploadEnd;
	String publicScope;
	int likeNumber;
	String momentWrite;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date writeDate;
	String fileRoute;
	String mediaName;
}
