package com.onsaem.web.common.service.impl;

import java.util.HashMap;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.onsaem.web.common.service.MailService;
import com.onsaem.web.common.service.MailDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	JavaMailSender emailSender;
	
	@Autowired
	SpringTemplateEngine templateEngine;
    
	@Override
    public Boolean sendMail(MailDTO mailDto, MultipartFile[] uploadfile){
    	try {
    		MimeMessage message = emailSender.createMimeMessage();
    		MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
	        //메일 제목 설정
	        helper.setSubject(mailDto.getSubject());   
	        
	        helper.setFrom(mailDto.getFrom());
	        
	        //첨부 파일 설정
	        if(uploadfile!=null) {
	        	for(MultipartFile file : uploadfile) {
	        	
			        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			        helper.addAttachment(MimeUtility.encodeText(fileName, "UTF-8", "B"), new ByteArrayResource(IOUtils.toByteArray(file.getInputStream())));
	        	
	        	} 
	        }           
	        
	      //템플릿에 전달할 데이터 설정
	        HashMap<String, String> emailValues = new HashMap<>();
	    	emailValues.put("title", mailDto.getTitle());
	    	emailValues.put("content", mailDto.getContent());
	        Context context = new Context();
	        emailValues.forEach((key, value)->{
	            context.setVariable(key, value);
	        });
	    	              
	        //메일 내용 설정 : 템플릿 프로세스
	        String html = templateEngine.process(mailDto.getTemplate(), context);
	        helper.setText(html, true);
	        
	        //템플릿에 들어가는 이미지 cid로 삽입
	        helper.addInline("backgroundImage", new ClassPathResource("static/test/mail.jpg"));
	        
	        
	        //수신자 개별 전송       
	//        for(String s : mailDto.getAddress()) {
	//        	helper.setTo(s);
	//        	emailSender.send(message);
	//        }
	        //수신자 한번에 전송
	        helper.setTo(mailDto.getAddress());
	        emailSender.send(message);
	        log.info("mail multiple send complete.");
	        return true;
		} catch (Exception e) {
			log.info("mail multiple send failed.");
			e.printStackTrace();
			return false;
		}

       
    }

}
