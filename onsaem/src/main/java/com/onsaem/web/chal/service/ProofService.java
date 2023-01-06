package com.onsaem.web.chal.service;

import java.util.List;

public interface ProofService {
		//인증샷 등록
		Integer inputProof(ProofVO vo);
		
		//챌린지별 전체 보기
		List<ProofVO> listProofAll(String chalId);
		
		//상세보기
		ProofVO getProof(String proofId);
		
		//여유되면 하는거 - 같은챌린지의 한 참가자가 인증샷 다 보기
		
		List<ProofVO> listProofOther(ProofVO vo);
		
		//인증샷 안보이게 하는거 - 관리자가 신고받았을 떄 
		Integer hideProof(String proofId);
		
		//댓글 달기
		Integer inputReply(RepliesVO vo);
		
		//댓삭
		Integer delReply(String replyId); 
		
		//좋아요
		Integer inputLike(LikeVO vo);
		
		//좋아요 취소
		Integer delLike(String likeId);
}
