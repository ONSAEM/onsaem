package com.onsaem.web.common.service;

import java.util.List;

public interface MailService {
	public Boolean sendMail(MailDTO mail, List<MediaVO> fileList);
}
