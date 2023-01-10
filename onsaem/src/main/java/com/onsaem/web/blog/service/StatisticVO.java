package com.onsaem.web.blog.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor 
public class StatisticVO {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date inflowTime;
	String inflowWay;
	String inflowKeyword;
	String blogId;
	String stat;
}
