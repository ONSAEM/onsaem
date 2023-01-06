package com.onsaem.web.chal.service;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NgoVO {
	String ngoId;
	String writerId;
	Integer ngoNo; //사업자등록번호
	String postalCode;
	String addr;
	String detailAddr;
	String email;
	Integer phone;
	String bank;
	String bankAccount;
	String condition;
	String ngoName;
	String classes; //분류 - 항시 일시 
}
