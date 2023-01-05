package com.onsaem.web.chal.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ProofVO {
	String proofId; //인증글코드
	String chalId; //챌리저스코드
	String proofWriter; //작성자
	String content; //내용
	Date writeDate; //
	String condition;
}
