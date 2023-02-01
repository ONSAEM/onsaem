package com.onsaem.web.member.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH24:MI:SS")
	private Date applyDate;
	
	private List<MediaVO> appliyFileList;
}
