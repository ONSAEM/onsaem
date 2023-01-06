package com.onsaem.web.chal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onsaem.web.chal.mapper.ProofMapper;
import com.onsaem.web.chal.service.LikeVO;
import com.onsaem.web.chal.service.ProofService;
import com.onsaem.web.chal.service.ProofVO;
import com.onsaem.web.chal.service.RepliesVO;

@Component
public class ProofServiceImpl implements ProofService {
	@Autowired ProofMapper proofMapper;

	@Override
	public Integer inputProof(ProofVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProofVO> listProofAll(String chalId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProofVO getProof(String proofId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProofVO> listProofOther(ProofVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer hideProof(String proofId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer inputReply(RepliesVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delReply(String replyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer inputLike(LikeVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delLike(String likeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
