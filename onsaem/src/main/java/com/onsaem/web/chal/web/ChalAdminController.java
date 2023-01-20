package com.onsaem.web.chal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.chal.service.ChalService;
import com.onsaem.web.chal.service.ChalVO;
import com.onsaem.web.chal.service.NgoService;
import com.onsaem.web.chal.service.ProofService;
import com.onsaem.web.common.service.MediaVO;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("/mypage")
public class ChalAdminController {
	@Autowired ChalService chalService;
	@Autowired ProofService proofService;
	@Autowired NgoService ngoService;
	
	//관리자의챌린저스 마이페이지 중 완료챌린저스 목록을 보기
	@RequestMapping(value="/AdminEndChals",method=RequestMethod.GET )
	public String endChalList(Model model) {
		//종료된 메소드 리스트 ㅎㅎ
		model.addAttribute("teams", chalService.AdminEndChals("팀"));
		
		model.addAttribute("teams", chalService.AdminEndChals("개인"));
		
		return "content/challengers/AdminEndChals";
	}
	
	//기부 증서 넣기
	@RequestMapping(value="/inputReceipt", method=RequestMethod.POST)
	@ResponseBody
	public String inputReceipt(@RequestBody MediaVO vo,Authentication authentication) {
		//세션에서 가져온 로그인 된 id값 
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		proofService.inputMedia(vo);
		
		ChalVO cvo = new ChalVO();
		cvo.setChalId(vo.getGroupId());
		cvo.setReceipt("첨부완료");
		chalService.updateRecipt(cvo);
		return "content/challengers/AdminEndChals";
	}
	
	//포인트 적립 
	
	//신고 리스트 띄우기
	
	//신고 제재
	
	//ngo관리리스트
	@RequestMapping(value="/ApplyNgoList", method=RequestMethod.GET)
	public String applyNgoLIst(Model model) {
		//승인받지 않은 리스트
		model.addAttribute("ponNgoes", ngoService.listNgo("신청"));
		
		//승인 받은 리스트
		model.addAttribute("ngoes", ngoService.listNgo("승인"));
		
		return "content/challengers/AdminNgoList";
	}
	
	//ngo신청 업데이트
	
	
	
}


