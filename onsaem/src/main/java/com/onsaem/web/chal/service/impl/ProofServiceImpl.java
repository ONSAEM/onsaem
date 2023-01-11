package com.onsaem.web.chal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.chal.mapper.ProofMapper;
import com.onsaem.web.chal.service.ProofService;
import com.onsaem.web.chal.service.ProofVO;
import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.RepliesVO;

@Component
public class ProofServiceImpl implements ProofService {
	@Autowired ProofMapper proofMapper;

	@Override
	public Integer inputProof(ProofVO vo) {
		// TODO Auto-generated method stub
		return proofMapper.inputProof(vo);
	}

	@Override
	public List<ProofVO> listProofAll(String chalId) {
		// TODO Auto-generated method stub
		return proofMapper.listProofAll(chalId);
	}

	@Override
	public ProofVO getProof(String proofId) {
		// TODO Auto-generated method stub
		return proofMapper.getProof(proofId);
	}

	@Override
	public List<ProofVO> listProofOther(ProofVO vo) {
		// TODO Auto-generated method stub
		return proofMapper.listProofOther(vo);
	}

	@Override
	public Integer hideProof(String proofId) {
		// TODO Auto-generated method stub
		return proofMapper.hideProof(null);
	}

	@Override
	public Integer inputReply(RepliesVO vo) {
		// TODO Auto-generated method stub
		return proofMapper.inputReply(vo);
	}

	@Override
	public Integer delReply(String replyId) {
		// TODO Auto-generated method stub
		return proofMapper.delReply(replyId);
	}

	@Override
	public Integer inputLike(LikeVO vo) {
		// TODO Auto-generated method stub
		return proofMapper.inputLike(vo);
	}

	@Override
	public Integer delLike(String likeId) {
		// TODO Auto-generated method stub
		return proofMapper.delLike(likeId);
	}
	

	@Override
	public List<LikeVO> listLike(LikeVO vo) {
		// TODO Auto-generated method stub
		return proofMapper.listLike(vo);
	}

	@Override
	public Integer inputMedia(MediaVO vo) {
		// TODO Auto-generated method stub
		return proofMapper.inputMedia(vo);
	}

	@Override
	public Integer updateMedia(MediaVO vo) {
		// TODO Auto-generated method stub
		return proofMapper.updateMedia(vo);
	}

	

	@Override
	public List<MediaVO> listMedia(MediaVO vo) {
		// TODO Auto-generated method stub
		return proofMapper.listMedia(vo);
	}

	@Override
	public MediaVO getMedia(String mediaId) {
		// TODO Auto-generated method stub
		return proofMapper.getMedia(mediaId);
	}

	@Override
	public MediaVO getMediaOption(MediaVO vo) {
		// TODO Auto-generated method stub
		return proofMapper.getMediaOption(vo);
	}

	@Override
	public List<MediaVO> getMyShotsForOne(MediaVO vo) {
		// TODO Auto-generated method stub
		return proofMapper.getMyShotsForOne(vo);
	}

	@Override
	public Integer countProof(ProofVO vo) {
		// TODO Auto-generated method stub
		return proofMapper.countProof(vo);
	}


}
