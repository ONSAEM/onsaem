package com.onsaem.web.common.service.impl;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.onsaem.web.common.service.MailService;
import com.onsaem.web.common.service.MailDTO;
import com.onsaem.web.common.service.MediaVO;

import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;

@Component
public class MailServiceImpl implements MailService{

	@Override
	public Boolean sendMail(MailDTO mail, List<MediaVO> fileList) {
//		MimeMessage message = JavaMailSender.createMimeMessage();
//		try {
//			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//			messageHelper.setSubject(mail.getSubject());
//			String htmlContent = mail.getContent();
//			messageHelper.setText(htmlContent, true);
//			messageHelper.setFrom("onsaem00@gmail.com", "온샘");
//			messageHelper.setTo(new InternetAddress(mail.getToEmail(), mail.getToName(), "UTF-8"));
//			for(MediaVO file : fileList) {
//		         if(file!=null) {
//		        	 DataSource dataSource = new FileDataSource(file.getFileRoute());
//		        	 messageHelper.addAttachment(MimeUtility.encodeText(file.getFileName(), "UTF-8", "B"), dataSource);
//		         }
//			}
//			JavaMailSender.send(message);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
			return false;
		} 
//	}
}
