package com.onsaem.web.chal.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.onsaem.web.common.service.ReportVO;
import com.onsaem.web.chal.service.ProofService;
import com.onsaem.web.chal.service.ReportService;
import com.onsaem.web.common.service.MediaVO;
@Controller
@CrossOrigin(origins="*")
public class ChalController {
	@Autowired ChalService chalService;
	@Autowired NgoService ngoService;
	@Autowired BankService bankService;
	@Autowired ProofService proofService;
	@Autowired ParticipantService partService;
	@Autowired ReportService reportService;
	@Autowired com.onsaem.web.common.service.MediaService mediaService;
	
	//챌린지 전체 리스트 확인
	@RequestMapping(value="/chalList",method=RequestMethod.GET)
	public String chalList(Model model) {
		String classes = "항시";
		model.addAttribute("ngoes", ngoService.listNgoClass(classes));
		model.addAttribute("chals", chalService.getChalAll());
		
		//기부금 순위로
		model.addAttribute("ranks", chalService.donateRank());
		return "content/challengers/chalMain";
	}
	
	//챌린지 조건 리스트 보기 - ngo별 
	@RequestMapping(value="/chalListOption",method=RequestMethod.GET)
	public String chalListNgo(Model model,@RequestParam(value="ngoName", required=false)String ngoName){
		String classes = "항시";
		model.addAttribute("ngoes", ngoService.listNgoClass(classes));
		model.addAttribute("chals", chalService.getChalNgoAll(ngoName));
		
		//기부금 순위로
		model.addAttribute("ranks", chalService.donateRank());
		return "content/challengers/chalMain";
	}
	
	//팀별
	@RequestMapping(value="/chalDepart",method=RequestMethod.GET)
	public String chalListTeam(Model model,@RequestParam(value="data", required=false)String data){
		System.out.println(data);
		
		switch(data) {
			case "팀" :
				model.addAttribute("chals",chalService.getChalTeamAll(data));
				model.addAttribute("ranks", chalService.donateRank());
				break;
			case "개인" :
				model.addAttribute("chals", chalService.getChalTeamAll(data));
				model.addAttribute("ranks", chalService.donateRank());
				break;
			case "모집중" :
				model.addAttribute("chals", chalService.beforeChals());
				model.addAttribute("ranks", chalService.donateRank());
				break;
			case "진행중" :
				model.addAttribute("chals", chalService.currentChals());
				model.addAttribute("ranks", chalService.donateRank());
				break;
			case "완료" :
				model.addAttribute("chals", chalService.endChals());
				model.addAttribute("ranks", chalService.donateRank());
				break;
		}
		return "content/challengers/chalMain";
	}
			
	//챌린지 한건 상세보기
	@RequestMapping(value="/detailChal",method=RequestMethod.GET)
	public String chalDetail(Model model, MediaVO vo, @RequestParam(value="chalId", required= true)String chalId, ParticipantVO pvo
			,Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		model.addAttribute("chals", chalService.getChal(chalId));
		vo.setGroupId(chalId);
		model.addAttribute("photoes", proofService.listMedia(vo));
		
		//썸네일가져오기
		model.addAttribute("thumbnail", chalService.thumnail(chalId));
		//인증샷예시 가져오기
		model.addAttribute("proofEx", chalService.proofEx(chalId));
		
		//아이디가 참가했는지 안했는지 확인,,- 수로 보냄
		pvo.setChalId(chalId);
		pvo.setParticipantId(userDetails.getUsername());
		//참가안했으면 0, 참가했으면 1
		model.addAttribute("cnt", partService.cntParticipant(pvo));
		return "content/challengers/chalDetail";
	}
	
	//등록페이지 일단,,
	@RequestMapping(value="/inputChal",method=RequestMethod.GET)
	public String inputChal(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return "content/challengers/inputChal";
	}
	
	//개인전 등록 페이지 이동
	@RequestMapping(value="/inputNormalChal",method=RequestMethod.GET)
	public String inputChalNormal(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
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
	public String insertNormalChal(HttpServletRequest req,ChalVO vo, ParticipantVO pvo
			,  MultipartFile[] uploadFile, MultipartFile[] natureFile, Authentication authentication) throws IllegalStateException, IOException {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		//Challengers테이블에 삽입
		vo.setMemberId(userDetails.getUsername());
		vo.setSubClass("개인");
		vo.setClasses("등록");
		vo.setTeamFee(0);
		vo.setReceipt(null);
		chalService.inputChal(vo);
		
		//Participant에 챌린저스 개최자 등록
		System.out.println("아이디" + vo.getChalId());
		System.out.println("기부금" + vo.getDonationFee());
		
		pvo.setChalId(vo.getChalId());
		pvo.setParticipantId(vo.getMemberId());
		pvo.setPrivateDonate(vo.getDonationFee());
		pvo.setBetPoint(0);
		partService.inputParticipant(pvo);
		
		
        //사진 업로드
		MediaVO mvo = new MediaVO();
		mvo.setGroupId(vo.getChalId());
		mvo.setGroups("챌린저스");
        for(int i=0;i<uploadFile.length;i++) {
			if(i==0) {
				mvo.setSubGroup("인증예시");
			}else {
				mvo.setSubGroup("썸네일");
			}			
			MultipartFile[] upload= {uploadFile[i]};
			mediaService.uploadMedia(upload, mvo);
		}		
			
			
		return "redirect:/chalList";
	}
	
	//챌린지 등록 - 팀전
	@RequestMapping(value="/inputTeamChal", method=RequestMethod.POST)
	public String insertTeamChal(HttpServletRequest req, ChalVO vo,ParticipantVO pvo
			, MultipartFile[] uploadFile, Authentication authentication) throws IllegalStateException, IOException {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();


		//Challengers테이블에 삽입
		vo.setMemberId(userDetails.getUsername());
		vo.setSubClass("팀");
		vo.setDonationFee(vo.getTeamFee());
		vo.setReceipt(null);
		chalService.inputChal(vo);
		
		//Participant에 챌린저스 개최자 등록
		System.out.println("아이디" + vo.getChalId());
		System.out.println("기부금" + vo.getDonationFee());
		
		pvo.setChalId(vo.getChalId());
		pvo.setParticipantId(vo.getMemberId());
		pvo.setPrivateDonate(vo.getDonationFee());
		
		partService.inputParticipant(pvo);
		
		
		//사진 업로드
				MediaVO mvo = new MediaVO();
				mvo.setGroupId(vo.getChalId());
				mvo.setGroups("챌린저스");
		        for(int i=0;i<uploadFile.length;i++) {
					if(i==0) {
						mvo.setSubGroup("인증예시");
					}else {
						mvo.setSubGroup("썸네일");
					}			
					MultipartFile[] upload= {uploadFile[i]};
					mediaService.uploadMedia(upload, mvo);
				}		
					
		return "redirect:/chalList";
	}
	
	
	
	//챌린지 참가 - 개인전 페이지 이동
	@RequestMapping(value="/applyChalFrm", method=RequestMethod.GET)
	public String applyChalFrm(@RequestParam(value="chalId", required= true)String chalId, Model model,MediaVO vo, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println(chalService.getChal(chalId));
		model.addAttribute("chals", chalService.getChal(chalId));
		vo.setGroupId(chalId);
		model.addAttribute("photoes", proofService.listMedia(vo));
		return "content/challengers/applyChalFrm";
	}
	
	//챌린지 참가 - 개인전 데이터 등록,,ㅎ
	@RequestMapping(value="/applyChalFrm", method=RequestMethod.POST)
	public String applyChal(ChalVO vo, ParticipantVO pvo, PaymentVO payvo, Authentication authentication) {
		//참가자 테이블
				UserDetails userDetails = (UserDetails) authentication.getPrincipal();

				pvo.setParticipantId(userDetails.getUsername());
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
		partService.inputParticipant(pvo);
		//챌린저스 테이블 수정
		
		vo.setChalId(pvo.getChalId());
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
	public String inputNgoPg(NgoVO vo,MultipartFile[] uploadFile, Model model ) throws IllegalStateException, IOException {
		model.addAttribute("banks", bankService.listBank());
		System.out.println("========================"+vo);
		//html페이지에서 받아오지 못하는 변수들 설정
		vo.setClasses("항시");
		vo.setCondition("신청");
		vo.setWriterId("jjinbbang");
		ngoService.inputNgo(vo);
		
		//사진 업로드
		MediaVO mvo = new MediaVO();
		mvo.setGroupId(vo.getNgoId());
		mvo.setGroups("기부처");
        for(int i=0;i<uploadFile.length;i++) {
			if(i==0) {
				mvo.setSubGroup("고유번호증");
			}else {
				mvo.setSubGroup("");
			}			
			MultipartFile[] upload= {uploadFile[i]};
			mediaService.uploadMedia(upload, mvo);
		}		
		return "content/challengers/inputNGO";
	}
	
	//신고
	@RequestMapping(value="/inputReport", method=RequestMethod.POST)
	@ResponseBody
	public String inputReport(@RequestBody ReportVO vo, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setFromId(userDetails.getUsername());
		reportService.inputReport(vo);
		return "content/challengers/chalDetail";
	}
	
	
	
}
