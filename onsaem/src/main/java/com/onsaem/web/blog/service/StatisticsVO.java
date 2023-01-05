package com.onsaem.web.blog.service;

import java.util.Date;


import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor 
public class StatisticsVO {
	Date inflowTime;
	String inflowWay;
	String inflowKeyword;
	String blogId;
	String stat;
}
