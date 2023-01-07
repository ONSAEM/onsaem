package com.onsaem.web.chal.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@CrossOrigin(origins="*")
public class ChalController {
	
	//챌린지 전체 리스트 확인
	@RequestMapping(value="/chalList",method=RequestMethod.GET)
	public String chalList(Model model) {
		
		return "content/challengers/chalMain";
	}
	
	//챌린지 조건 리스트 보기 - 마이페이지, 전체리스트에서 조건활용 등등

			
	//챌린지 한건 상세보기
	
	@RequestMapping(value="/detailChal",method=RequestMethod.GET)
	public String chalDetail(Model model) {
		
		return "content/challengers/chalDetail";
	}
	
	//챌린지 등록 - 개인전
	
	//챌린지 등록 - 팀전
	
	//챌린지 참가 - 개인전
	
	//챌린지 참가 - 팀전
	
	//챌린지 취소 
	
	//기부처 등록
	
	
	
}
