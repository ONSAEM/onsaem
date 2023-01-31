package com.onsaem.web.member.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.member.service.ApplyMemberVO;
import com.onsaem.web.member.service.ApplyService;

@Controller
@CrossOrigin(origins = "*")
public class ApplyController {
	
	@Autowired
	ApplyService applyService;
	
	// 회원등록
	@RequestMapping(value = "/insertApply", method = RequestMethod.POST)
	@ResponseBody
	public String insertApply(MultipartFile[] appliyFile, ApplyMemberVO vo) throws IllegalStateException, IOException {
		return applyService.insertApply(appliyFile, vo);
	}
}
