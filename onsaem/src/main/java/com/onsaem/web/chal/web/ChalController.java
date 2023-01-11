package com.onsaem.web.chal.web;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.onsaem.web.chal.service.BankService;
import com.onsaem.web.chal.service.ChalService;
import com.onsaem.web.chal.service.ChalVO;
import com.onsaem.web.chal.service.MediaService;
import com.onsaem.web.chal.service.NgoService;
import com.onsaem.web.chal.service.NgoVO;
import com.onsaem.web.chal.service.ParticipantService;
import com.onsaem.web.chal.service.ParticipantVO;
import com.onsaem.web.common.service.PaymentVO;
import com.onsaem.web.chal.service.ProofService;
import com.onsaem.web.common.service.MediaVO;
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
		String classes = "항시";
		model.addAttribute("ngoes", ngoService.listNgoClass(classes));
		model.addAttribute("chals", chalService.getChalAll());
		return "content/challengers/chalMain";
	}
	
	//챌린지 조건 리스트 보기 - ngo별 
	@RequestMapping(value="/chalListOption",method=RequestMethod.GET)
	public String chalListNgo(Model model,@RequestParam(value="ngoName", required=false)String ngoName){
		String classes = "항시";
		model.addAttribute("ngoes", ngoService.listNgoClass(classes));
		model.addAttribute("chals", chalService.getChalNgoAll(ngoName));
		return "content/challengers/chalMain";
	}
	
	@RequestMapping(value="/chalListTeam",method=RequestMethod.GET)
	public String chalListTeam(Model model,String value){
		model.addAttribute("chals", chalService.getChalTeamAll(value));
		return "content/challengers/chalMainTeam";
	}
			
	//챌린지 한건 상세보기
	@RequestMapping(value="/detailChal",method=RequestMethod.GET)
	public String chalDetail(Model model, MediaVO vo, @RequestParam(value="chalId", required= true)String chalId) {
		model.addAttribute("chals", chalService.getChal(chalId));
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
	public String insertNormalChal(HttpServletRequest req,ChalVO vo, MediaVO mvo, ParticipantVO pvo, List<MultipartFile> uploadFile) {
		
		//Challengers테이블에 삽입
		vo.setMemberId("podo");
		vo.setSubClass("개인");
		vo.setTeamFee(0);
		chalService.inputChal(vo);
		
		//Participant에 챌린저스 개최자 등록
		System.out.println("아이디" + vo.getChalId());
		System.out.println("기부금" + vo.getDonationFee());
		
		pvo.setChalId(vo.getChalId());
		pvo.setParticipantId(vo.getMemberId());
		pvo.setPrivateDonate(vo.getDonationFee());
		pvo.setBetPoint(0);
		partService.inputParticipant(pvo);
		
		
		//사진 등록
		//session = req.getSession();
		String realFolder = "/challengers/img/";
        File dir = new File(realFolder);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
		
		
		//넘어온 파일 리스트로 저장
		//List<MultipartFile> files = mhsq.getFiles("uploadFile");
			for(int i=0; i<uploadFile.size(); i++) {
				//원래 파일 이름 
				String originName = uploadFile.get(i).getOriginalFilename();
				//groupId 지정  - chal 글번호루,,
				mvo.setGroupId(vo.getChalId());
				mvo.setFileName(originName);
				//경로?주소?지정
				mvo.setFileRoute(realFolder);
				
				mvo.setSubGroup("챌린저스");
				System.out.println(mvo);
				proofService.inputMedia(mvo);
				
			}
		return "redirect:/chalList";
	}
	
	//챌린지 등록 - 팀전
	@RequestMapping(value="/inputTeamChal", method=RequestMethod.POST)
	public String insertTeamChal(HttpServletRequest req, ChalVO vo, MediaVO mvo, ParticipantVO pvo, List<MultipartFile> uploadFile) {

		//Challengers테이블에 삽입
		vo.setMemberId("podo");
		vo.setSubClass("팀");
		vo.setDonationFee(vo.getTeamFee());
		chalService.inputChal(vo);
		
		//Participant에 챌린저스 개최자 등록
		System.out.println("아이디" + vo.getChalId());
		System.out.println("기부금" + vo.getDonationFee());
		
		pvo.setChalId(vo.getChalId());
		pvo.setParticipantId(vo.getMemberId());
		pvo.setPrivateDonate(vo.getDonationFee());
		
		partService.inputParticipant(pvo);
		
		
		//사진 등록
		//session = req.getSession();
		String realFolder = "/challengers/img/";
        File dir = new File(realFolder);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
		
		
		//넘어온 파일 리스트로 저장
		//List<MultipartFile> files = mhsq.getFiles("uploadFile");
			for(int i=0; i<uploadFile.size(); i++) {
				//원래 파일 이름 
				String originName = uploadFile.get(i).getOriginalFilename();
				//groupId 지정  - chal 글번호루,,
				mvo.setGroupId(vo.getChalId());
				mvo.setFileName(originName);
				//경로?주소?지정
				mvo.setFileRoute(realFolder);
				
				mvo.setSubGroup("챌린저스");
				System.out.println(mvo);
				proofService.inputMedia(mvo);
				
			}
		return "redirect:/chalList";
	}
	
	
	
	//챌린지 참가 - 개인전 페이지 이동
	@RequestMapping(value="/applyChalFrm", method=RequestMethod.GET)
	public String applyChalFrm(@RequestParam(value="chalId", required= true)String chalId, Model model,MediaVO vo) {
		System.out.println(chalService.getChal(chalId));
		model.addAttribute("chals", chalService.getChal(chalId));
		vo.setGroupId(chalId);
		model.addAttribute("photoes", proofService.listMedia(vo));
		return "content/challengers/applyChalFrm";
	}
	
	//챌린지 참가 - 개인전 데이터 등록,,ㅎ
	@RequestMapping(value="/applyChalFrm", method=RequestMethod.POST)
	public String applyChal(ChalVO vo, ParticipantVO pvo, PaymentVO payvo) {
		//참가자 테이블
		
				pvo.setParticipantId("hodu");
				pvo.setBetPoint(0);
				partService.inputParticipant(pvo);
				//챌린저스 테이블 수정
				
				vo.setChalId(pvo.getChalId());
				chalService.updateDonate(vo);
				System.out.println("group_id" + pvo.getChalId());
				
				//결제 테이블
				payvo.setGroupId(pvo.getChalId());
				payvo.setPrice(pvo.getPrivateDonate());
				payvo.setPayerId(pvo.getParticipantId());
				payvo.setPaymentMethod("카카오페이");
				partService.inputPayment(payvo);
				
				
				return "redirect:/chalList";
	}
	
	//챌린지 참가 - 팀전 페이지 이동
	@RequestMapping(value="/applyChalTeamFrm", method=RequestMethod.GET)
	public String applyChalTeamFrm(@RequestParam(value="chalId", required= true)String chalId, Model model,MediaVO vo) {
		System.out.println(chalService.getChal(chalId));
		model.addAttribute("chals", chalService.getChal(chalId));
		vo.setGroupId(chalId);
		model.addAttribute("photoes", proofService.listMedia(vo));

		return "content/challengers/applyChalTeamFrm";
	}
	
	//챌린지 참가 - 팀 등록
	@RequestMapping(value="/applyChalTeamFrm", method=RequestMethod.POST)
	public String applyChalTeam(ChalVO vo, ParticipantVO pvo, PaymentVO payvo) {
		//참가자 테이블
		
		pvo.setParticipantId("hodu");
		pvo.setBetPoint(0); //수정필요 
		partService.inputParticipant(pvo);
		//챌린저스 테이블 수정
		
		chalService.updateDonate(vo);
		
		//결제 테이블
		System.out.println(pvo.getChalId());
		payvo.setGroupId(pvo.getChalId());
		payvo.setPrice(pvo.getPrivateDonate());
		payvo.setPayerId(pvo.getParticipantId());
		payvo.setPaymentMethod("카카오페이");
		partService.inputPayment(payvo);
		
		
		return "redirect:/chalList";
	}
	
	//챌린지 취소 
	
	//기부처 등록페이지 이동
	@RequestMapping(value="/inputNgo", method=RequestMethod.GET)
	public String inputNgo(Model model) {
		model.addAttribute("banks", bankService.listBank());
		return "content/challengers/inputNGO";
	}
	
	//기부처 등록
	@RequestMapping(value="/inputNgo", method=RequestMethod.POST)
	@ResponseBody //ajax
	public String inputNgoPg(NgoVO vo, Model model) {
		model.addAttribute("banks", bankService.listBank());
		System.out.println("========================"+vo);
		//html페이지에서 받아오지 못하는 변수들 설정
		vo.setClasses("항시");
		vo.setCondition("신청");
		vo.setWriterId("jjinbbang");
		ngoService.inputNgo(vo);
		return "content/challengers/inputNGO";
	}
	//기부처 승인
	
	
	
}
