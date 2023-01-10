package com.onsaem.web.chal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onsaem.web.chal.service.ChalService;
import com.onsaem.web.chal.service.ChalVO;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("/mypage")
public class ChalMypageController {

	@Autowired ChalService chalService;
	
	//권한 - 일반회원의 챌린저스 마이페이지 첫화면, 진행중 챌린지 띄우깅
	@RequestMapping(value="/myEndChal",method=RequestMethod.GET)
	public String myCurrentChalList(Model model,@RequestParam(value="userId", required=false)String userId,ChalVO vo) {
		vo.setMemberId(userId);
		model.addAttribute("chalList", chalService.myCurentChal(vo));
		return "content/challengers/MyCurrentChal";
	}
	
	//권한 - 일반회원의 챌린저스 마이페이지 - 시작전 챌린지 모음
	@RequestMapping(value="/myBeforeChal",method=RequestMethod.GET)
	public String myBeforeChalList(Model model,@RequestParam(value="userId", required=false)String userId,ChalVO vo) {
		vo.setMemberId(userId);
		model.addAttribute("chalList", chalService.myBeforeChal(vo));
		return "content/challengers/MyCurrentChal";
	}
		
		
	//권한 - 일반회원의 챌린저스 마이페이지- 완료된 챌린지 모음
	@RequestMapping(value="/myCurrentChal",method=RequestMethod.GET)
	public String myEndChalList(Model model,@RequestParam(value="userId", required=false)String userId,ChalVO vo) {
		vo.setMemberId(userId);
		model.addAttribute("chalList", chalService.myEndChal(vo));
		return "content/challengers/MyCurrentChal";
	}
	
	
}
