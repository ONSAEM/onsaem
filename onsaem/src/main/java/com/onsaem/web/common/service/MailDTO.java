package com.onsaem.web.common.service;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {
	private String fromAddress;
    private List<String> toAddrList = new ArrayList<>();
    private List<String> ccAddrList = new ArrayList<>();
    private List<String> bccAddrList = new ArrayList<>();
    private String subject; // 제목
    private String content; // 메일 내용
    private boolean isUseHtmlYn; // 메일 형식이 HTML인지 여부(true, false)
}
