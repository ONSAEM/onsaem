package com.onsaem.web.common.service;

import org.springframework.web.multipart.MultipartFile;

public interface MailService {
	public Boolean sendMail(MailDTO mailDto, MultipartFile[] uploadfile);
}
