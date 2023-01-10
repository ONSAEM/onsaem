package com.onsaem.web.member.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class MemberVO {
	String password;
	String nickname;
	String email;
	String phone;
	String postalCode;
	String addr;
	String detailAddr;
	String bank;
	String bankAccount;
	String businessNumber;
	String right;
	int point;
	String status;
	String memberId;
}
