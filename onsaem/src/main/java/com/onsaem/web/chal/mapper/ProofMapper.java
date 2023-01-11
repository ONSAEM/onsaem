package com.onsaem.web.chal.mapper;

import java.util.List;

import com.onsaem.web.chal.service.ProofVO;
import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.RepliesVO;

public interface ProofMapper {
	//인증샷 등록
	Integer inputProof(ProofVO vo);
	
	//챌린지별 전체 보기 - 조인써야된다, 수정필요
	List<ProofVO> listProofAll(String chalId);
	
	//상세보기 - 조인시발
	ProofVO getProof(String proofId);
	
	//여유되면 하는거 - 같은챌린지의 한 참가자가 인증샷 다 보기 - 조인해야됨, 근데 안할거같아
	
	List<ProofVO> listProofOther(ProofVO vo);
	
	//인증샷 안보이게 하는거 - 관리자가 신고받았을 떄 
	Integer hideProof(ProofVO vo);
	
	//댓글 달기
	Integer inputReply(RepliesVO vo);
	
	//댓삭
	Integer delReply(String replyId); 
	
	//좋아요
	Integer inputLike(LikeVO vo);
	
	//좋아요 취소
	Integer delLike(String likeId);
	
	//좋아요 리스트 뽑기
	List<LikeVO> listLike(LikeVO vo);
	
	//사진등록
	Integer inputMedia(MediaVO vo);
	
	//사진 안보이게 하는거도 포함
	Integer updateMedia(MediaVO vo);
	
	//사진 리스트 쫙 뽑기
	List<MediaVO> listMedia(MediaVO vo);
	
	
	//사진 한개 보기
	MediaVO getMedia(String mediaId);
	
	//사진 한개보기 조건
	MediaVO getMediaOption(MediaVO vo);

}
