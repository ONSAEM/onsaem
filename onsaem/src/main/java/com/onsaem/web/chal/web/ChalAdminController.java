package com.onsaem.web.chal.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.chal.service.ChalService;
import com.onsaem.web.chal.service.ChalVO;
import com.onsaem.web.chal.service.NgoService;
import com.onsaem.web.chal.service.NgoVO;
import com.onsaem.web.chal.service.ParticipantService;
import com.onsaem.web.chal.service.ParticipantVO;
import com.onsaem.web.chal.service.ProofService;
import com.onsaem.web.chal.service.ProofVO;
import com.onsaem.web.common.service.MediaVO;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("/mypage")
public class ChalAdminController {
	@Autowired ChalService chalService;
	@Autowired ProofService proofService;
	@Autowired NgoService ngoService;
	@Autowired com.onsaem.web.common.service.MediaService mediaService;
	@Autowired ParticipantService partService;
	
	//관리자의챌린저스 마이페이지 중 완료챌린저스 목록을 보기
	@RequestMapping(value="/AdminEndChals",method=RequestMethod.GET )
	public String endChalList(Model model) {
		//종료된 메소드 리스트 ㅎㅎ
		model.addAttribute("teams", chalService.AdminEndChals("팀"));
		
		model.addAttribute("ones", chalService.AdminEndChals("개인"));
		
		return "content/challengers/AdminEndChals";
	}
	
	//기부 증서 넣기
	@RequestMapping(value="/inputReceipt", method=RequestMethod.POST)
	@ResponseBody
	public String inputReceipt(MediaVO vo,Authentication authentication,  MultipartFile[] uploadFile) throws IllegalStateException, IOException {
		//세션에서 가져온 로그인 된 id값 
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		vo.setGroups("챌린저스");
        for(int i=0;i<uploadFile.length;i++) {
			if(i==0) {
				vo.setSubGroup("영수증");
			}else {
				vo.setSubGroup("영수증");
			}			
			MultipartFile[] upload= {uploadFile[i]};
			mediaService.uploadMedia(upload, vo);
		}		
		
		ChalVO cvo = new ChalVO();
		cvo.setChalId(vo.getGroupId());
		cvo.setReceipt("첨부완료");
		chalService.updateRecipt(cvo);
		
		
		return "redirect:/mypage/AdminEndChals";
	}
	
	//해당 챌린지 참가 인원 리스트 뽑기
	@RequestMapping(value="/getParticipant", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getParticipant(String chalId){
		Map<String, Object> map = new HashMap<String, Object>();
		//해당 챌린지 참가자 리스트 뽑기
		map.put("list", partService.listParticipantAll(chalId));
		
		return map;
	}
	
	//선택한 아이디의 인증정보 뽑기
	@RequestMapping(value="getInfo4Point", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getInfo4Point(@RequestBody ProofVO vo){
		Map<String, Object> map = new HashMap<String, Object>();
		ChalVO cvo = chalService.getChal(vo.getChalId());
		Date end = cvo.getEndDate();
		Date start = cvo.getStartDate();
		Date today = new Date();
		long days = (end.getTime() - start.getTime())/(24*60*60*1000)+1;
		map.put("days", days);
		
		vo.setCondition("정상");
		map.put("cnt", proofService.countProof(vo));
		
		ParticipantVO pvo = new ParticipantVO();
		pvo.setChalId(vo.getChalId());
		pvo.setParticipantId(vo.getProofWriter());
		map.put("user", partService.getParticipant(pvo));
		
		return map;
	}
	
	//포인트 적립 
	
	//신고 리스트 띄우기
	
	//신고 제재
	
	//ngo관리리스트
	@RequestMapping(value="/ApplyNgoList", method=RequestMethod.GET)
	public String applyNgoLIst(Model model) {
		//승인받지 않은 리스트
		model.addAttribute("ponNgoes", ngoService.notApproveList());
		
		//승인 받은 리스트
		model.addAttribute("ngoes", ngoService.listNgo("승인"));
		
		return "content/challengers/AdminNgoList";
	}
	
	//ngo신청 승인
	@RequestMapping(value="/approveNgo", method=RequestMethod.POST)
	public String approveNgo(@RequestBody NgoVO vo){
		Map<String,Object> map = new HashMap<String, Object>();
		
		ngoService.updateCondition(vo);
		
		
		return "redirect:/mypage/ApplyNgoList";
	}
	
	//ngo신청 반려
	@RequestMapping(value="/rejectNgo", method=RequestMethod.POST)
	public String rejectNgo(@RequestBody NgoVO vo){
		Map<String,Object> map = new HashMap<String, Object>();
		
		ngoService.rejectNgo(vo);
		

		return "redirect:/mypage/ApplyNgoList";
	}
	
	
	
}


