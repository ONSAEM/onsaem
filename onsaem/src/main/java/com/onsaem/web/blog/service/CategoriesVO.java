package com.onsaem.web.blog.service;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class CategoriesVO {
	String categoryId;
	String categoryName;
	String blogId;
	int orderNum;
	String publicScope;
}
