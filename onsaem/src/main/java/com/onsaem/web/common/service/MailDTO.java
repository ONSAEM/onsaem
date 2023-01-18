package com.onsaem.web.common.service;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailDTO {
	private String from;
    private String[] address;
    private String[] ccAddress;
    private String subject;
    private String title;
    private String content;
    private String template;
    private List<MediaVO> attachFileList = new ArrayList<>();
}
