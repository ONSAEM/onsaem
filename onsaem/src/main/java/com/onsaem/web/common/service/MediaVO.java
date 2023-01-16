package com.onsaem.web.common.service;

import lombok.Data;
import lombok.NoArgsConstructor;

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
	int mediaOrder;
	String uuid;
	
	 public MediaVO() {}
	 public MediaVO(String uuid, String fileName, String mediaType) {
         this.uuid = uuid;
         this.fileName = fileName;
         this.mediaType = mediaType;
     }
	 
	//챌린저스에 변수 추가
	String proofWriter;
}
