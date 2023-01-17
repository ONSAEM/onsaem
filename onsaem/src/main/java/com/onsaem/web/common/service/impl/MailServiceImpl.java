package com.onsaem.web.common.service.impl;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.onsaem.web.common.service.MailService;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;

@Component
public class MailServiceImpl implements MailService{

	@Override
	public Boolean sendMail() {
//		JavaMailSender mailSender;
//		MimeMessage message = mailSender.createMimeMessage();
//		try {
//			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//			messageHelper.setSubject("[공지] 회원 가입 안내");
//			String htmlContent = "안녕하세요, 반갑습니다.";
//			messageHelper.setText(htmlContent, true);
//			messageHelper.setFrom("gz.kyungho@gmail.com", "갱짱");
//			messageHelper.setTo(new InternetAddress('보낼 메일', '', "UTF-8"));
//			DataSource dataSource = new FileDataSource("c:\\책목록.xlsx");
//			messageHelper.addAttachment(MimeUtility.encodeText("책목록.xlsx", "UTF-8", "B"), dataSource);
//			mailSender.send(message);
//		} catch (Exception e) {
//			e.printStackTrace();
			return false;
		} 
}
