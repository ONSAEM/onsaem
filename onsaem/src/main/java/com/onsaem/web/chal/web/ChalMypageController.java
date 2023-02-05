package com.onsaem.web.chal.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.onsaem.web.chal.service.ProofVO;
import com.onsaem.web.chal.service.ReportService;
import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.PaymentVO;
import com.onsaem.web.common.service.RefundVO;
import com.onsaem.web.common.service.RepliesVO;
import com.onsaem.web.common.service.ReportVO;
import com.onsaem.web.member.service.MemberService;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("/mypage")
public class ChalMypageController {

	@Autowired ChalService chalService;
	@Autowired ProofService proofService;
	@Autowired ParticipantService partService;
	@Autowired MemberService memService;
	@Autowired BankService bankService;
	@Autowired NgoService ngoService;
	@Autowired ReportService reportService;
	@Autowired MediaService mediaService;

	
	//권한 - 일반회원의 챌린저스 마이페이지 첫화면, 진행중 챌린지 띄우깅
	@RequestMapping(value="/myCurrentChal",method=RequestMethod.GET)
	public String myCurrentChalList(Model model,ChalVO vo, Authentication authentication,ProofVO pvo ) {
		//세션에서 가져온 로그인 된 id값 
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		vo.setParticipantId(userDetails.getUsername());
		//로그인 유저가 참가중인 챌린저들,,,
		model.addAttribute("chals", chalService.myCurentChal(vo));
		
		
		return "content/challengers/MypageCurrentChal";
	}
	
	//인증샷 넣기 ! 
	@RequestMapping(value="/myCurrentChal",method=RequestMethod.POST)
	@ResponseBody
	public String inputProof(ProofVO pvo,  MultipartFile[] uploadFile, Authentication authentication) throws IllegalStateException, IOException {
		//세션에서 가져온 로그인 된 id값 
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String id = userDetails.getUsername();
		
		//인증테이블에 값넣기
		pvo.setProofWriter(id);
		pvo.setCondition("정상");
		proofService.inputProof(pvo);
		
		//미디어 테이블에 값넣기
		MediaVO vo = new MediaVO();
		
		vo.setGroupId(pvo.getProofId());
		vo.setSubGroup("챌린저스인증");
		vo.setGroups(pvo.getChalId());
		mediaService.uploadMedia(uploadFile, vo);

		return "true";
	}
	
	//권한 - 일반회원의 챌린저스 마이페이지 - 시작전 챌린지 모음
	@RequestMapping(value="/myBeforeChal",method=RequestMethod.GET)
	public String myBeforeChalList(Model model,ChalVO vo, Authentication authentication) {
		
		//세션에서 가져온 로그인 된 id값 
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		vo.setParticipantId(userDetails.getUsername());
		//이용자가 참가한 시작 전인 챌린지들 가져오기.
		List<ChalVO> list=chalService.myBeforeChal(vo);
		System.out.println(list);
		System.out.println(list.size());
		
		if(list.size()>0){
			model.addAttribute("chals", list);
		} else {
			model.addAttribute("msg", "시작 예정인 챌린지가 없습니다!");
		}
		
		//썸네일 조회
		model.addAttribute("pics", proofService.myChalThumnails(vo));
		
		//은행들 가져오기
		model.addAttribute("banks", bankService.listBank());

		//개인 정보 가져오기
		model.addAttribute("user", memService.getMember("hodu"));
		
		return "content/challengers/MypageBeforeChal";
	}
	
	//마이페이지 시작전 챌린지 모음 - 취소하기
	@RequestMapping(value="/myBeforeChal",method=RequestMethod.POST)
	@ResponseBody
	public String CancelChal(@RequestBody RefundVO rvo,ParticipantVO pvo, PaymentVO yvo, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		//환불테이블에 추가
		
		partService.inputRefund(rvo);
		
		//participant테이블에서 삭제 - 이것도 update로 해야할지 판단필요
		pvo.setChalId(rvo.getGroupId());
		pvo.setParticipantId(userDetails.getUsername());
		partService.delParticipant(pvo);
		
		//결제 테이블에서 update
		yvo.setPaymentId(rvo.getPaymentId());
		partService.updateForRefund(yvo);
		

		return "content/challengers/MypageBeforeChal";
	}
		
		
	//권한 - 일반회원의 챌린저스 마이페이지- 완료된 챌린지 모음
	@RequestMapping(value="/myEndChal",method=RequestMethod.GET)
	public String myEndChalList(Model model,ChalVO vo, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		vo.setParticipantId(userDetails.getUsername());
		model.addAttribute("chals", chalService.myEndChal(vo));
		
		return "content/challengers/MypageEndChal";
	}
	
	//마이페이지의 나의 인증현황
	@RequestMapping(value="/myChalStatus", method=RequestMethod.GET)
	public String myChalStatus(Model model, @RequestParam(value="chalId", required=false)String chalId, MediaVO vo, ProofVO pvo,HttpServletRequest request,Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		//세션에서 가져온 로그인 된 id값
		String id = userDetails.getUsername();
		//chalId="CH1";
//		//챌린저스 한건 정보
		model.addAttribute("chal",chalService.getChal(chalId));
		
		ParticipantVO tvo = new ParticipantVO();
		tvo.setChalId(chalId);
		tvo.setParticipantId(id);
		//참가자 정보
		model.addAttribute("user", partService.getParticipant(tvo));
		
		ChalVO cvo = chalService.getChal(chalId);
		Date end = cvo.getEndDate();
		Date start = cvo.getStartDate();
		Date today = new Date();
		long dday = (today.getTime() - end.getTime())/(24*60*60*1000)+1;
		model.addAttribute("dday", dday);
		long days = (end.getTime() - start.getTime())/(24*60*60*1000)+1;

		model.addAttribute("total", days);
		
		//		
		//해당 챌린지에 대해 내가 입력한 모든 인증샷 싹다 가져오기 - chalId, memberid
		vo.setGroupId(chalId);
		//vo.setProofWriter("hodu");
		vo.setProofWriter(id);
		vo.setGroups(chalId);
		model.addAttribute("myPhotoes", proofService.getMyShotsForOne(vo));
		
//		//Proofs 테이블에서 count(*) 해야함 - 조건이 성공인거, 작성자 아이디, 챌린지 아이디 필요
		pvo.setChalId(chalId);
		pvo.setProofWriter(id);
		pvo.setCondition("정상");
		
		model.addAttribute("cntGood", proofService.countProof(pvo));
		
//		//Proofs 테이블에서 count(*) 해야함 - 조건이 성공인거, 작성자 아이디, 챌린지 아이디 필요
		pvo.setChalId(chalId);
		pvo.setProofWriter(id);
		pvo.setCondition("실패");
		
		model.addAttribute("cntBad", proofService.countProof(pvo));
		
		return "content/challengers/MyChalStatus1";
	}
	
	
	//얘 마이페이지의 - 2번째 페이지
	@RequestMapping(value="/myChalStatus2", method=RequestMethod.GET)
	public String myChalStatus2(Model model, @RequestParam(value="chalId", required=false)String chalId,Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		model.addAttribute("chal",chalService.getChal(chalId));
		chalId = "CH1";
		if(chalService.getChal(chalId).getSubClass()=="팀") {
			//팀전
			ParticipantVO pvo = new ParticipantVO();
			pvo.setChalId(chalId);
			pvo.setParticipantId(userDetails.getUsername());
			//우리팀 구하기
			String myTeam = partService.getParticipant(pvo).getTeam();
			model.addAttribute("myTeam", myTeam);
			pvo.setTeam(myTeam);
			//성공인증샷수 구해야함
			model.addAttribute("MyTeamCnt", proofService.cntTeamProof(pvo));
			//우리팀 인원수
			model.addAttribute("MyteamSize", partService.listParticipantAll(pvo).size());
			
		}else{
			//개인전
			ParticipantVO tvo = new ParticipantVO();
			tvo.setChalId(chalId);
			//인간 수
			model.addAttribute("ChalSize", partService.listParticipantAll(tvo).size());
			//성공인증샷 수
			model.addAttribute("ChalCnt", proofService.listProofAll(chalId).size());
		}
		//한 챌린지에 대한 모든 사람들의 인증글 가져오기
		model.addAttribute("proofs", proofService.listProofAll(chalId));
		return "content/challengers/MyChalStatus2";
	}
	
	//마이페이지 2번쨰 페이지의 모달창내용 ㅎㅎ
	@RequestMapping(value="/proofDetail", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> proofDetail(String proofId, Authentication authentication){
		//아이디
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String user = userDetails.getUsername();
		
		 Map<String, Object> map = new HashMap<String, Object>();
		 
		  //게시글 한개 내용 가져오기, 인증샷 model.addAttribute("proof",
		 System.out.println(proofId);
		 map.put("brd",proofService.getProof(proofId));
		 System.out.println("//////////////////////"+proofService.getProof(proofId));
		 //댓글 리스트 
		 map.put("repllies",proofService.listReply(proofId));
		//좋아요 수
		  LikeVO vo = new LikeVO(); 
		  vo.setGroupId(proofId);
		 map.put("likeCnt", proofService.cntChalLike(vo));
		
		 //좋아요 했는지 여부 확인
		 LikeVO vo2 = new LikeVO();
		 vo2.setGroupId(proofId);
		 vo2.setMemberId(user);
		map.put("checkLike",proofService.checkLike(vo2));
		 
		 return map ;
		 
	}
	
	//좋아용
	@RequestMapping(value="/addChalLike", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addChalLike(String groupId, Authentication authentication) {
		 Map<String, Object> map = new HashMap<String, Object>();
		 LikeVO vo = new LikeVO();
		//좋아요 넣기
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String user = userDetails.getUsername();
		vo.setGroupId(groupId);
		vo.setMemberId(user);
		proofService.inputLike(vo);
		
		//좋아요 갯수
		map.put("likeCnt", proofService.cntChalLike(vo));
		
		//좋아요 여부 확인
		map.put("checkLike",proofService.checkLike(vo));
		return map;
	}
	
	//좋아요 취소
	@RequestMapping(value="/delChalLike", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delChalLike(String groupId, Authentication authentication) {
		Map<String, Object> map = new HashMap<String, Object>();
		 LikeVO vo = new LikeVO();
		 
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String user = userDetails.getUsername();
		vo.setMemberId(user);
		vo.setGroupId(groupId);
		//좋아요 삭제
		proofService.delLike(vo);
		//좋아요 갯수
		map.put("likeCnt", proofService.cntChalLike(vo));
				
		//좋아요 여부 확인
		map.put("checkLike",proofService.checkLike(vo));
		return map;
	}
	
	//댓글 작성 ㅎㅎ
	@RequestMapping(value="/inputReply", method=RequestMethod.POST)
	@ResponseBody
	public List<RepliesVO> inputReply(@RequestBody RepliesVO vo,Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String id = userDetails.getUsername();
		vo.setWriterId(id);
		System.out.println(vo.getGroupId());
		
		proofService.inputReply(vo);
		
		
		//댓글 리스트 가져오기? 
		List<RepliesVO> list = proofService.listReply(vo.getGroupId());

		return list;
	}
	
	//내가 신청한 ngo목록 보기,,
	@RequestMapping(value="/myApplyNgo", method=RequestMethod.GET)
	public String myApplyNgo(Model model,Authentication authentication) {
		//로그인 아이디 불러오기
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String id = userDetails.getUsername();
		
		//내가 신청한 ngo리스트를 가져오기
		
		if(ngoService.myApplies(id).size()==0) {
			model.addAttribute("msg", "신청하신 목록이 없습니다.");
		}else {
			model.addAttribute("ngoes", ngoService.myApplies(id));
		}
		
		return "content/challengers/MypageApplyNgo";
	}
	
	//ngo 재신청
	@RequestMapping(value="/reSubmit", method=RequestMethod.POST)
	public String reSubmit(@RequestBody NgoVO vo, Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String id = userDetails.getUsername();
		System.out.println(vo);
		vo.setClasses("항시");
		ngoService.updateNgo(vo);
		return "redirect:/mypage/myApplyNgo";
		
	}
	
	//영수증 보기
	@RequestMapping(value="/seeRecepit", method=RequestMethod.POST)
	@ResponseBody
	public MediaVO seeRecepit(String groupId,Authentication authentication){
		Map<String,Object>map = new HashMap<String,Object>();
		//아이디
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String user = userDetails.getUsername();
		
		//subgroup이 기부영수증이고, groupId를 이용해서 영수증 찾기
		
		return proofService.getReceipt(groupId);
	}
	
	//나의 포인트 확인
	@RequestMapping(value="checkPoint", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkPoint(String chalId,Authentication authentication){
		Map<String,Object>map = new HashMap<String,Object>();
		
		//아이디
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String user = userDetails.getUsername();
		ParticipantVO vo = new ParticipantVO();
		vo.setParticipantId(user);
		vo.setChalId(chalId);
		
		//성공률 계산할 것
		//공통 필요 - 총 챌린저스 일수 
		ChalVO cvo = chalService.getChal(chalId);
		Date end = cvo.getEndDate();
		Date start = cvo.getStartDate();
		Integer check =  cvo.getUsercnt();
		long days = (end.getTime() - start.getTime())/(24*60*60*1000)+1;
		map.put("totaldays", days);

		if(check==0) {
			//개인전 일때 
//			//이거로 다 가능 
			//나의 포인트 조회
			map.put("user", partService.getParticipant(vo));		
		}else {
			//팀전일때 성공률 계산
			
			//A팀 성공률 구하기 ㅎㅎ
			vo.setTeam("A");
			map.put("cntA", proofService.cntTeamProof(vo));
			//A팀 인원수
			map.put("Asize", partService.listParticipantAll(vo).size());
			
			//B팀 성공률 구하기 ~
			vo.setTeam("B");
			map.put("cntB", proofService.cntTeamProof(vo));
			//b팀인원수
			map.put("Bsize", partService.listParticipantAll(vo).size());
			//총 일수 구하기, 총 기부비 정보
			map.put("totalInfo" ,chalService.getChal(chalId));
			
			//나의 포인트 조회
			map.put("user", partService.getParticipant(vo));			
		}

		return map;
	}
	
	//신고
	@RequestMapping(value="inputReport", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> inputChalReport(@RequestBody ReportVO vo,Authentication authentication) {
		Map<String,Object> map = new HashMap<>();
		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String user = userDetails.getUsername();
		
		vo.setFromId(user);
		String msg ="신고 완료되었습니다.";
		reportService.inputReport(vo);
		map.put("msg", msg);
		
		
		return map;
	}
	
	
	
	

}
