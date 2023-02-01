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

/**
 * 작성자 - 주소현
 * 작성 내용 - 신청관리
 */


@Controller
@CrossOrigin(origins = "*")
public class ApplyController {

	@Autowired
	ApplyService applyService;

	// 회원등록
	@RequestMapping(value = "/insertApply", method = RequestMethod.POST)
	@ResponseBody
	public String insertApply(MultipartFile[] applyFile, ApplyMemberVO vo) throws IllegalStateException, IOException {
		return applyService.insertApply(applyFile, vo);
	}

	// 회원등록
	@RequestMapping(value = "/applyCOM", method = RequestMethod.GET)
	public String applyCOM() {
		return "content/member/applyCOM";
	}
}
