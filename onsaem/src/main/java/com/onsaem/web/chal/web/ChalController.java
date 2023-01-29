package com.onsaem.web.chal.web;

import java.io.IOException;

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

import com.onsaem.web.chal.service.BankService;
import com.onsaem.web.chal.service.ChalService;
import com.onsaem.web.chal.service.ChalVO;
import com.onsaem.web.chal.service.NgoService;
import com.onsaem.web.chal.service.NgoVO;
import com.onsaem.web.chal.service.ParticipantService;
import com.onsaem.web.chal.service.ParticipantVO;
import com.onsaem.web.chal.service.ProofService;
import com.onsaem.web.chal.service.ReportService;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.PaymentVO;
import com.onsaem.web.common.service.ReportVO;
import com.onsaem.web.member.service.MemberService;

/**
 * 
 * @author 박이현
 * 챌린저스 리스트, 상세보기 페이지
 *
 */

@Controller
@CrossOrigin(origins="*")
public class ChalController {
	@Autowired ChalService chalService;
	@Autowired NgoService ngoService;
	@Autowired BankService bankService;
	@Autowired ProofService proofService;
	@Autowired ParticipantService partService;
	@Autowired ReportService reportService;
	@Autowired MediaService mediaService;
	@Autowired MemberService memberService;
	
	//챌린지 전체 리스트 확인
	@RequestMapping(value="/chalList",method=RequestMethod.GET)
	public String chalList(Model model,ChalVO vo, Paging paging) {
		
		String classes = "항시";
		model.addAttribute("ngoes", ngoService.listNgoClass(classes));
		model.addAttribute("chals", chalService.getChalAll(vo, paging));
		model.addAttribute("paging", paging);
		//기부금 순위로
		model.addAttribute("ranks", chalService.donateRank());
		return "content/challengers/chalMain";
	}
	
	//챌린지 조건 리스트 보기 - ngo별 
	@RequestMapping(value="/chalListOption",method=RequestMethod.GET)
	public String chalListNgo(Model model,@RequestParam(value="ngoName", required=false)String ngoName, ChalVO vo,  Paging paging){
		String classes = "항시";
		model.addAttribute("ngoes", ngoService.listNgoClass(classes));
		vo.setNgoName(ngoName);
		model.addAttribute("chals", chalService.getChalNgoAll(vo, paging));
		
		//기부금 순위로
		model.addAttribute("ranks", chalService.donateRank());
		return "content/challengers/chalMain";
	}
	
	//팀별
	@RequestMapping(value="/chalDepart",method=RequestMethod.GET)
	public String chalListTeam(Model model,@RequestParam(value="data", required=false)String data,  Paging paging){
		System.out.println(data);
		ChalVO vo = new ChalVO();
		switch(data) {
			case "팀" :
				vo.setSubClass(data);
				model.addAttribute("chals",chalService.getChalTeamAll(vo, paging));
				model.addAttribute("ranks", chalService.donateRank());
				break;
			case "개인" :
				vo.setSubClass(data);
				model.addAttribute("chals", chalService.getChalTeamAll(vo, paging));
				model.addAttribute("ranks", chalService.donateRank());
				break;
			case "모집중" :
				model.addAttribute("chals", chalService.beforeChals(vo, paging));
				model.addAttribute("ranks", chalService.donateRank());
				break;
			case "진행중" :
				model.addAttribute("chals", chalService.currentChals(vo, paging));
				model.addAttribute("ranks", chalService.donateRank());
				break;
			case "완료" :
				model.addAttribute("chals", chalService.endChals(vo, paging));
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
		
		//썸네일가져오기
		model.addAttribute("thumbnail", chalService.thumnail(chalId));
		//인증샷예시 가져오기
		model.addAttribute("proofEx", chalService.proofEx(chalId));
		
		//아이디가 참가했는지 안했는지 확인,,- 수로 보냄
		pvo.setChalId(chalId);
		pvo.setParticipantId(userDetails.getUsername());
		//참가안했으면 0, 참가했으면 1
		model.addAttribute("cnt", partService.cntParticipant(pvo));
		
		//
		Integer nowStart = chalService.getChal(chalId).getNowStart();
		Integer nowEnd = chalService.getChal(chalId).getNowEnd();
		
		if(nowStart>=0 && nowEnd<=0) {
			//진행중인 챌린지
			//인증샷 파티
			model.addAttribute("proofs", proofService.listProofAll(chalId));
		}else if(nowStart>0 && nowEnd>0) {
			//완료된 챌린지
			//영수증 이미지 파일 가져오기
			model.addAttribute("receipt", proofService.getReceipt(chalId));
			model.addAttribute("proofs", proofService.listProofAll(chalId));
		}
		
		//상세 밑에 추천 컨텐츠로 넣을 조만간 시작하는 챌린지 모음들
		model.addAttribute("ddays", chalService.ddayStartRank());
		
		//회원정보 가져가기
		model.addAttribute("user", memberService.getMember(userDetails.getUsername()));
		
		return "content/challengers/chalDetail";
	}
	
	//등록페이지 일단,,
	@RequestMapping(value="/inputChal",method=RequestMethod.GET)
	public String inputChal(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		model.addAttribute("user", userDetails.getUsername());
		return "content/challengers/inputChal";
	}
	
	//개인전 등록 페이지 이동
	@RequestMapping(value="/inputNormalChal",method=RequestMethod.GET)
	public String inputChalNormal(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		model.addAttribute("user", memberService.getMember(userDetails.getUsername()));
		String classes = "항시";
		model.addAttribute("ngoes", ngoService.listNgoClass(classes));
		
		return "content/challengers/inputNormalChal";
	}
	
	//팀전 등록 페이지 이동
	@RequestMapping(value="/inputTeamChal",method=RequestMethod.GET)
	public String inputChalTeam(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		model.addAttribute("user", memberService.getMember(userDetails.getUsername()));
		String classes = "항시";
		model.addAttribute("ngoes", ngoService.listNgoClass(classes));
		
		return "content/challengers/inputTeamChal";
	}
	
	//챌린지 등록 - 개인전
	@RequestMapping(value="/inputNormalChal", method=RequestMethod.POST)
	@ResponseBody
	public String insertNormalChal(ChalVO vo, ParticipantVO pvo
			,  MultipartFile[] uploadFile, Authentication authentication) throws IllegalStateException, IOException {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		//Challengers테이블에 삽입
		vo.setMemberId(userDetails.getUsername());
		vo.setSubClass("개인");
		vo.setClasses("등록");
		vo.setTeamFee(0);
		vo.setReceipt(null);
		vo.setUsercnt(0);
		chalService.inputChal(vo);
		
		//Participant에 챌린저스 개최자 등록
		System.out.println("아이디" + vo.getChalId());
		System.out.println("기부금" + vo.getDonationFee());
		
		pvo.setChalId(vo.getChalId());
		pvo.setParticipantId(vo.getMemberId());
		pvo.setPrivateDonate(vo.getDonationFee());
		pvo.setTeam(null);
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
        
        //결제 테이블
        PaymentVO yvo = new PaymentVO();
        yvo.setPrice(vo.getDonationFee());
        yvo.setGroupId(vo.getChalId());
        yvo.setPayerId(vo.getMemberId());
        yvo.setPaymentId(vo.getPaymentId());
        yvo.setPaymentMethod(vo.getPaymentMethod());
		partService.inputPayment(yvo);
	
			
		return "true";
	}
	
	//챌린지 등록 - 팀전
	@RequestMapping(value="/inputTeamChal", method=RequestMethod.POST)
	@ResponseBody
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
		pvo.setBetPoint(vo.getBetPoint());
		pvo.setTeam("A");
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
        
      //결제 테이블
        PaymentVO yvo = new PaymentVO();
        yvo.setPrice(vo.getDonationFee());
        yvo.setGroupId(vo.getChalId());
        yvo.setPayerId(vo.getMemberId());
        yvo.setPaymentId(vo.getPaymentId());
        yvo.setPaymentMethod(vo.getPaymentMethod());
        yvo.setStatus("결제완료");
		partService.inputPayment(yvo);
					
		return "true";
	}
	
	
	
	//챌린지 참가 - 개인전 페이지 이동
	@RequestMapping(value="/applyChalFrm", method=RequestMethod.GET)
	public String applyChalFrm(@RequestParam(value="chalId", required= true)String chalId, Model model,MediaVO vo, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println(chalService.getChal(chalId));
		model.addAttribute("chal", chalService.getChal(chalId));
		vo.setGroupId(chalId);
		model.addAttribute("photoes", proofService.listMedia(vo));
		model.addAttribute("user", memberService.getMember(userDetails.getUsername()));
		return "content/challengers/applyChalFrm";
	}
	
	//챌린지 참가 - 개인전 데이터 등록,,ㅎ
	@RequestMapping(value="/applyChalFrm", method=RequestMethod.POST)
	@ResponseBody
	public String applyChal(@RequestBody PaymentVO payvo,ParticipantVO pvo,ChalVO vo, Authentication authentication) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		//결제 테이블
		
		payvo.setPayerId(userDetails.getUsername());
		partService.inputPayment(payvo);
		
		//참가자 테이블
		pvo.setParticipantId(userDetails.getUsername());
		pvo.setChalId(payvo.getGroupId());
		pvo.setPrivateDonate(payvo.getPrice());
		pvo.setBetPoint(0);
		pvo.setTeam(null);
		partService.inputParticipant(pvo);
		
		//챌린저스 테이블 수정
		vo.setChalId(payvo.getGroupId());
		vo.setDonationFee(payvo.getDonationFee());
		chalService.updateDonate(vo);
		System.out.println("group_id" + pvo.getChalId());

		return "redirect:/chalList";
	}
	
	//챌린지 참가 - 팀전 페이지 이동
	@RequestMapping(value="/applyChalTeamFrm", method=RequestMethod.GET)
	public String applyChalTeamFrm(@RequestParam(value="chalId", required= true)String chalId, Model model,
			MediaVO vo, Authentication authentication) {
		//세선에 저장된 로그인 아이디
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		//로그인 사용자 정보 조회
		model.addAttribute("user", memberService.getMember(userDetails.getUsername()));
		System.out.println(chalService.getChal(chalId));
		model.addAttribute("chal", chalService.getChal(chalId));
		vo.setGroupId(chalId);
		model.addAttribute("photoes", proofService.listMedia(vo));

		return "content/challengers/applyChalTeamFrm";
	}
	
	//챌린지 참가 - 팀 참가
	@RequestMapping(value="/applyChalTeamFrm", method=RequestMethod.POST)
	@ResponseBody
	public String applyChalTeam(@RequestBody  PaymentVO payvo, ChalVO vo, ParticipantVO pvo, Authentication authentication) {
		//세선에 저장된 로그인 아이디
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		//결제 테이블
		payvo.setPayerId(userDetails.getUsername());
		partService.inputPayment(payvo);
		
		//챌린저스 테이블에서 인원수 조회, 참가자 테이블에서 인원수 조회
		String chalId = payvo.getGroupId();
		ChalVO cvo = chalService.getChal(chalId);
		//챌린저스 총 인원수
		Integer total = cvo.getUsercnt();
		
		//참가인원수
		ParticipantVO tvo = new ParticipantVO();
		tvo.setChalId(chalId);
		Integer cnt = partService.listParticipantAll(tvo).size();
		
		//참가인원수가 총인원수의 반 이상이면 B팀으로 설정
		if(cnt>=(total*0.5)) {
			pvo.setTeam("B");
		}else {
			pvo.setTeam("A");
		}

		//참가자 테이블
		pvo.setChalId(chalId);
		pvo.setParticipantId(userDetails.getUsername());
		pvo.setBetPoint(payvo.getBetPoint());
		pvo.setPrivateDonate(payvo.getPrice());
		
		partService.inputParticipant(pvo);
		
		//챌린저스 테이블 수정
		vo.setChalId(chalId);
		vo.setDonationFee(payvo.getDonationFee());
		chalService.updateDonate(vo);

		return "redirect:/chalList";
	}
	
	
	
	//기부처 등록페이지 이동
	@RequestMapping(value="/inputNgo", method=RequestMethod.GET)
	public String inputNgo(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		model.addAttribute("user", userDetails.getUsername());
		model.addAttribute("banks", bankService.listBank());
		return "content/challengers/inputNGO";
	}
	
	//기부처 등록
	@RequestMapping(value="/inputNgo", method=RequestMethod.POST)
	public String inputNgoPg(NgoVO vo,MultipartFile[] uploadFile, Model model,  Authentication authentication) throws IllegalStateException, IOException {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		model.addAttribute("banks", bankService.listBank());
		System.out.println("========================"+vo);
		//html페이지에서 받아오지 못하는 변수들 설정
		vo.setClasses("항시");
		vo.setCondition("신청");
		vo.setWriterId(userDetails.getUsername());
		ngoService.inputNgo(vo);
		
		//사진 업로드
		MediaVO mvo = new MediaVO();
		mvo.setGroupId(vo.getNgoId());
		System.out.println(vo.getNgoId());
		mvo.setGroups("기부처");
		mvo.setSubGroup("고유번호증");
		mediaService.uploadMedia(uploadFile, mvo);
				
		return "redirect:/chalList";
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
