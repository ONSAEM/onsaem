package com.onsaem.web.chal.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.chal.service.ChalService;
import com.onsaem.web.chal.service.ChalVO;
import com.onsaem.web.chal.service.MediaService;
import com.onsaem.web.chal.service.ProofService;
import com.onsaem.web.chal.service.ProofVO;
import com.onsaem.web.common.service.MediaVO;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("/mypage")
public class ChalMypageController {

	@Autowired ChalService chalService;
	@Autowired ProofService proofService;

	
	//권한 - 일반회원의 챌린저스 마이페이지 첫화면, 진행중 챌린지 띄우깅
	@RequestMapping(value="/myCurrentChal",method=RequestMethod.GET)
	public String myCurrentChalList(Model model,ChalVO vo, HttpServletRequest req,ProofVO pvo ) {
		HttpSession session = req.getSession();
//		//세션에서 가져온 로그인 된 id값
		String id = (String)session.getAttribute("id");
		
		vo.setParticipantId("hodu");
		//로그인 유저가 참가중인 챌린저들,,,
		model.addAttribute("chals", chalService.myCurentChal(vo));
		
		List<ChalVO> list=chalService.myCurentChal(vo);
		List<Integer> plist = new ArrayList<>();
		
		for(int i=0;i<list.size();i++) {
			System.out.println("==================="+list.get(i).getChalId());
			pvo.setChalId(list.get(i).getChalId());
			pvo.setProofWriter("hodu");
			Integer n = proofService.checkProof(pvo);
			plist.add(n);
			
		}
		System.out.println(plist);
		
		//썸네일덜 ~ 나중에 디자인 하기 싫으면 걍 뺴고 표로 만들면 됨 ~
		model.addAttribute("pics", proofService.myChalThumnails(vo));
		
		//인증했는지 안했는지 확인용 
		model.addAttribute("checks", plist);
		
		return "content/challengers/MyCurrentChal";
	}
	
	//인증샷 넣기 ! 
	@RequestMapping(value="/myCurrentChal",method=RequestMethod.POST)
	
	public String inputProof(@RequestBody ProofVO pvo, HttpServletRequest req) {
		HttpSession session = req.getSession();
		MediaVO vo = new MediaVO();

		
//		//세션에서 가져온 로그인 된 id값
		String id = (String)session.getAttribute("id");
		
		//인증테이블에 값넣기
		pvo.setProofWriter("hodu");
		pvo.setCondition("정상");
		proofService.inputProof(pvo);
		
		//미디어 테이블에 값넣기
		String realFolder = "/challengers/img/";
		
		vo.setGroupId(pvo.getProofId());
		vo.setFileName(pvo.getFileName());
		vo.setFileRoute(realFolder);
		vo.setSubGroup("챌린저스인증");
		proofService.inputMedia(vo);
		return "redirect:/mypage/myCurrentChal";
	}
	
	//권한 - 일반회원의 챌린저스 마이페이지 - 시작전 챌린지 모음
	@RequestMapping(value="/myBeforeChal",method=RequestMethod.GET)
	public String myBeforeChalList(Model model,ChalVO vo, HttpServletRequest req) {
		HttpSession session = req.getSession();
//		//세션에서 가져온 로그인 된 id값
		String id = (String)session.getAttribute("id");
		
		vo.setMemberId("hodu");
		model.addAttribute("chals", chalService.myBeforeChal(vo));
		
		//썸네일덜 ~ 나중에 디자인 하기 싫으면 걍 뺴고 표로 만들면 됨 ~
		model.addAttribute("pics", proofService.myChalThumnails(vo));
		
		return "content/challengers/MyBeforeChal";
	}
	
	//마이페이지 시작전 챌린지 모음 - 취소하기
	@RequestMapping(value="/myBeforeChal",method=RequestMethod.POST)
	public String CabcelChal(Model model,ChalVO vo, HttpServletRequest req) {
		HttpSession session = req.getSession();
//		//세션에서 가져온 로그인 된 id값
		String id = (String)session.getAttribute("id");
		
		vo.setMemberId("hodu");
		
		//participant테이블에서 삭제갈김
		
		
		//결제 테이블에 추가
		
		
		//환불테이블에 추가
		
		return "content/challengers/MyCurrentChal";
	}
		
		
	//권한 - 일반회원의 챌린저스 마이페이지- 완료된 챌린지 모음
	@RequestMapping(value="/myEndChal",method=RequestMethod.GET)
	public String myEndChalList(Model model,@RequestParam(value="userId", required=false)String userId,ChalVO vo) {
		vo.setMemberId(userId);
		model.addAttribute("chalList", chalService.myEndChal(vo));
		return "content/challengers/MyCurrentChal";
	}
	
	//마이페이지의 나의 인증현황
	@RequestMapping(value="/myChalStatus", method=RequestMethod.GET)
	public String myChalStatus(Model model, @RequestParam(value="chalId", required=false)String chalId, MediaVO vo, ProofVO pvo,HttpServletRequest request) {
//		HttpSession session=request.getSession();
//		//세션에서 가져온 로그인 된 id값
//		String id = (String)session.getAttribute("id");
//		//챌린저스 한건 정보
//		model.addAttribute("chal",chalService.getChal(chalId));
//		
//		//해당 챌린지에 대해 내가 입력한 모든 인증샷 싹다 가져오기 - chalId, memberid
//		vo.setGroupId(chalId);
//		vo.setProofWriter(id);
//		model.addAttribute("myPhotoes", proofService.getMyShotsForOne(vo));
//		
//		//Proofs 테이블에서 count(*) 해야함 - 조건이 성공인거, 작성자 아이디, 챌린지 아이디 필요
//		pvo.setChalId(chalId);
//		pvo.setProofWriter(id);
//		pvo.setCondition("성공");
//		
//		model.addAttribute("cntGood", proofService.countProof(pvo));
//		
//		//Proofs 테이블에서 count(*) 해야함 - 조건이 성공인거, 작성자 아이디, 챌린지 아이디 필요
//		pvo.setChalId(chalId);
//		pvo.setProofWriter(id);
//		pvo.setCondition("실패");
//		
//		model.addAttribute("cntBad", proofService.countProof(pvo));
		
		return "content/challengers/MyChalStatus";
	}
	
	@RequestMapping(value="/check", method=RequestMethod.GET)
	public String check() {
		return "content/challengers/mytem";
	}
	
	
	//얘 마이페이지의 - 2버냬ㅉ 페이지, value 파일명 다고치삼
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test() {
		return "content/challengers/testFile";
	}
	
	
}
