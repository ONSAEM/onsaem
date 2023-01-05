package com.onsaem.web.blog.service;

import java.util.Date;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MomentsVO {
	String momentId;
	String blogId;
	String addUrl;
	Date uploadStart;
	Date uploadEnd;
	String publicScope;
	int likeNumber;
	String momentWrite;
	Date writeDate;
}
