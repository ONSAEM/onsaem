package com.onsaem.web.chal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onsaem.web.chal.service.ChalService;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("/mypage")
public class ChalAdminController {
	@Autowired ChalService chalService;
	
	//완료된 챌린지 목록 띄우기
	@RequestMapping(value="/endChals",method=RequestMethod.GET )
	public String endChalList(Model model) {
		//종료된 메소드 리스트 ㅎㅎ
		model.addAttribute("chals", chalService.endChals());
		
		return "content/challengers/AdminEndChals";
	}
	
	//기부 증서 넣기
	
	//포인트 적립 
	
	//신고 리스트 띄우기
	
	//신고 제재
	
	//ngo신청 리스트
	
	//ngo신청 업데이트
	
	
}


