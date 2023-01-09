package com.onsaem.web.chal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.chal.service.BankService;
import com.onsaem.web.chal.service.ChalService;
import com.onsaem.web.chal.service.ChalVO;
import com.onsaem.web.chal.service.MediaService;
import com.onsaem.web.chal.service.MediaVO;
import com.onsaem.web.chal.service.NgoService;
import com.onsaem.web.chal.service.NgoVO;
import com.onsaem.web.chal.service.ParticipantService;
import com.onsaem.web.chal.service.ParticipantVO;
import com.onsaem.web.chal.service.ProofService;
@Controller
@CrossOrigin(origins="*")
public class ChalController {
	@Autowired ChalService chalService;
	@Autowired NgoService ngoService;
	@Autowired BankService bankService;
	@Autowired ProofService proofService;
	@Autowired ParticipantService partService;
	
	//챌린지 전체 리스트 확인
	@RequestMapping(value="/chalList",method=RequestMethod.GET)
	public String chalList(Model model) {
		model.addAttribute("chals", chalService.getChalAll());
		return "content/challengers/chalMain";
	}
	
	//챌린지 조건 리스트 보기 - ngo별 
	@RequestMapping(value="/chalListOption",method=RequestMethod.GET)
	public String chalListNgo(Model model,String ngoName){
		model.addAttribute("chals", chalService.getChalNgoAll(ngoName));
		return "content/challengers/chalMainNgo";
	}
	
	@RequestMapping(value="/chalListTeam",method=RequestMethod.GET)
	public String chalListTeam(Model model,String value){
		model.addAttribute("chals", chalService.getChalTeamAll(value));
		return "content/challengers/chalMainTeam";
	}
			
	//챌린지 한건 상세보기
	@RequestMapping(value="/detailChal",method=RequestMethod.GET)
	public String chalDetail(Model model, String chalId, MediaVO vo) {
		model.addAttribute("chal", chalService.getChal(chalId));
		vo.setGroupId(chalId);
		model.addAttribute("photoes", proofService.listMedia(vo));
		return "content/challengers/chalDetail";
	}
	
	//등록페이지 일단,,
	@RequestMapping(value="/inputChal",method=RequestMethod.GET)
	public String inputChal(Model model) {
		return "content/challengers/inputChal";
	}
	
	//개인전 등록 페이지 이동
	@RequestMapping(value="/inputNormalChal",method=RequestMethod.GET)
	public String inputChalNormal(Model model) {
		String classes = "항시";
		model.addAttribute("ngoes", ngoService.listNgoClass(classes));
		model.addAttribute("banks", bankService.listBank());
		return "content/challengers/inputNormalChal";
	}
	
	//팀전 등록 페이지 이동
	@RequestMapping(value="/inputTeamChal",method=RequestMethod.GET)
	public String inputChalTeam(Model model) {
		String classes = "항시";
		model.addAttribute("ngoes", ngoService.listNgoClass(classes));
		return "content/challengers/inputTeamChal";
	}
	
	//챌린지 등록 - 개인전
	@RequestMapping(value="/inputNormalChal", method=RequestMethod.POST)
	public String insertNormalChal(@RequestParam MultipartFile[] uploadfile,ChalVO vo, MediaVO mvo, ParticipantVO pvo) {
		System.out.println(uploadfile);
		chalService.inputChal(vo, pvo);
		mvo.setGroupId(vo.getChalId());
		//업로드먼저
		proofService.inputMedia(mvo);
		/*
		 * List for(int i=0; i<)
		 */
		
		
		
		return "redirect:/chalList";
	}
	
	//챌린지 등록 - 팀전
	@RequestMapping(value="/inputTeamChal", method=RequestMethod.POST)
	public ChalVO insertTeamChal(@RequestParam String data,ChalVO vo, MediaVO mvo, ParticipantVO pvo) {
		System.out.println("============="+data);
		chalService.inputChal(vo, pvo);
		proofService.inputMedia(mvo);
		return vo;
	}
	//챌린지 등록 - 개인전
	
	
	//챌린지 참가 - 개인전 페이지 이동
	
	//챌린지 참가 - 팀전 페이지 이동 
	
	//챌린지 취소 
	
	//기부처 등록페이지 이동
	@RequestMapping(value="/inputNgo", method=RequestMethod.GET)
	public String inputNgo() {
		return "content/challengers/inputNGO";
	}
	//기부처 등록
	@RequestMapping(value="/inputNgo", method=RequestMethod.POST)
	public String inputNgoPg(NgoVO vo) {
		vo.setClasses("항시");
		vo.setCondition("신청");
		ngoService.inputNgo(vo);
		return "content/challengers/inputNGO";
	}
	//기부처 승인
	
	
	
}
