package com.onsaem.web.blog.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogMediaVO {
	String mediaId;
	String groupId;
	String groups;
	String subGroup;
	String fileName;
	String fileRoute;
	String mediaName;
}
