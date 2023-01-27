package com.onsaem.web.common.service;



import lombok.Data;

@Data
public class MediaVO {
	String mediaId;
	String groupId;
	String groups;
	String subGroup;
	String fileName;
	String fileRoute;
	String mediaName;
	String mediaType;
	int mediaOrder = 0;
	String uuid;
	
	//챌린저스에 변수 추가
	String proofWriter;
	String proofId;
}
