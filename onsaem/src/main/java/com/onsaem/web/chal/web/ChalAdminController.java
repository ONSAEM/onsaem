package com.onsaem.web.chal.web;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;

/**
 * 작성자 - 박이현 , 
 * 작성 내용 - 챌린저스 관리자용 페이지
 */

@Controller
@CrossOrigin(origins="*")
@RequestMapping("/mypage")
public class ChalAdminController {
	
	@Autowired ChalService chalService;
	@Autowired ProofService proofService;
	@Autowired NgoService ngoService;
	@Autowired MediaService mediaService;
	@Autowired ParticipantService partService;
	
	//관리자의챌린저스 마이페이지 중 완료챌린저스 목록을 보기
	@RequestMapping(value="/AdminEndChals",method=RequestMethod.GET )
	public String endChalList(Model model) {
		//종료된 메소드 리스트 ㅎㅎ
		model.addAttribute("teams", chalService.AdminEndChals("팀"));
		
		model.addAttribute("ones", chalService.AdminEndChals("개인"));
		
		//정산됫는지 확인하는 메소드 필요
		
		return "content/challengers/AdminEndChals";
	}
	
	//기부 증서 넣기
	@RequestMapping(value="/inputReceipt", method=RequestMethod.POST)
	public String inputReceipt(MediaVO vo,Authentication authentication,  MultipartFile[] uploadFile) throws IllegalStateException, IOException {
		//세션에서 가져온 로그인 된 id값 
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		//이미지 첨부

		vo.setGroups("챌린저스");
		vo.setSubGroup("영수증");
		mediaService.uploadMedia(uploadFile, vo);
		
        //챌린저스 테이블 상태 업데이트
		ChalVO cvo = new ChalVO();
		cvo.setChalId(vo.getGroupId());
		cvo.setReceipt("첨부완료");
		chalService.updateRecipt(cvo);
		
		
		return "redirect:/AdminEndChals";
	}
	
	//챌린저스 개인전 포인트 적립 - 해당 챌린지 참가 인원 리스트 뽑기
	@RequestMapping(value="/getParticipant", method=RequestMethod.POST)
	@ResponseBody
	public List<ParticipantVO> getParticipant(String chalId){
		ParticipantVO vo = new ParticipantVO();
		vo.setChalId(chalId);
		
		return partService.listParticipantAll(vo);
	}
	
	//챌린저스 팀전 각 팀 정보 뽑기 ㅎ 
	@RequestMapping(value="/getTeams", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTeams(String chalId){
		Map<String, Object> map = new HashMap<String, Object>();
		ParticipantVO vo = new ParticipantVO();
		//a팀 정상인증 갯수 구하기
		vo.setTeam("A");
		vo.setChalId(chalId);
		map.put("A", proofService.cntTeamProof(vo));
		
		//A팀 인원수
		map.put("Asize", partService.listParticipantAll(vo).size());
				
		//b팀 정상 인증 갯수 구하기
		vo.setTeam("B");
		vo.setChalId(chalId);
		map.put("B", proofService.cntTeamProof(vo));
		
		//b팀인원수
		map.put("Bsize", partService.listParticipantAll(vo).size());
		
		//총 일수 구하기, 총 기부비 정보
		map.put("totalInfo" ,chalService.getChal(chalId));
		
		
		
		return map;
	}
	
	//개인전 포인트 정산 - 선택한 아이디의 인증정보 뽑기
	@RequestMapping(value="/getInfo4Point", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getInfo4Point(@RequestBody ProofVO vo){
		Map<String, Object> map = new HashMap<String, Object>();
		ChalVO cvo = chalService.getChal(vo.getChalId());
		Integer days = cvo.getTotal();
		map.put("days", days);
		
		vo.setCondition("정상");
		map.put("cnt", proofService.countProof(vo));
		
		ParticipantVO pvo = new ParticipantVO();
		pvo.setChalId(vo.getChalId());
		pvo.setParticipantId(vo.getProofWriter());
		map.put("user", partService.getParticipant(pvo));
		
		return map;
	}
	
	//개인전 - 포인트 적립
	@RequestMapping(value="/inputPoint", method=RequestMethod.POST)
	@ResponseBody
	public String inputPoint(@RequestBody ParticipantVO vo) {
		//참가자테이블 result, resultPoint 업데이트
		
		//member테이블 업데이트
		
		return "true";
	}
	
	
	//팀전 포인트 적립
	@RequestMapping(value="sharePoint", method=RequestMethod.POST)
	@ResponseBody
	public String sharePoint(@RequestBody ParticipantVO vo){
		
		String winner = vo.getWinner();
		String loser = vo.getLoser();
		Integer thatPoint = vo.getThatPoint();
		String chalId = vo.getChalId();
		
		ParticipantVO pvo = new ParticipantVO();
		//승팀 포인트 정산
		pvo.setChalId(chalId);
		pvo.setTeam(winner);
		pvo.setThatPoint(thatPoint);
		pvo.setResult("승리");
		partService.updateResultPoint(pvo);
		chalService.updateMemberPoint(pvo);
		
		//패팀 포인트 정산
		Integer losePoint = vo.getThatPoint()*-1;
		System.out.println(losePoint);
		pvo.setChalId(chalId);
		pvo.setTeam(loser);
		pvo.setResult("패배");
		pvo.setThatPoint(losePoint);
		partService.updateResultPoint(pvo);
		chalService.updateMemberPoint(pvo);
		
		return "true";
	}
	
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
	@ResponseBody
	public String approveNgo(@RequestBody NgoVO vo){
		Map<String,Object> map = new HashMap<String, Object>();
		
		ngoService.updateCondition(vo);
		
		
		return "true"; //Collections.singletonMap("result", "true")  {result:true}
	}
	
	//ngo신청 반려
	@RequestMapping(value="/rejectNgo", method=RequestMethod.POST)
	@ResponseBody
	public String rejectNgo(@RequestBody NgoVO vo){
			System.out.println("vovovovo"+vo);
		ngoService.rejectNgo(vo);
		

		return "true";
	}
	
	
	
}


