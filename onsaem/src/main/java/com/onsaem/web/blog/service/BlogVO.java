package com.onsaem.web.blog.service;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class BlogVO {
	String blogId;
	String blogName;
	String introduction;
	String blogUrl;
}
