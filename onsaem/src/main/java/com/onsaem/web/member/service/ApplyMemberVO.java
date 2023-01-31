package com.onsaem.web.member.service;

import java.util.List;

import com.onsaem.web.common.service.MediaVO;

import lombok.Data;

@Data
public class ApplyMemberVO {
	private String applyId;
	private String password;
	private String nickname;
	private String email;
	private String phone;
	private String postalCode;
	private String addr;
	private String detailAddr;
	private String bank;
	private String bankAccount;
	private String businessNumber;
	private String memberClass;
	private String applyStatus;
	private String rejectReason;
	private String memberId;
	private String detailExplain;
	
	private List<MediaVO> appliyFile;
}
