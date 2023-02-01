package com.onsaem.web.member.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.member.mapper.ApplyMapper;
import com.onsaem.web.member.service.ApplyMemberVO;
import com.onsaem.web.member.service.ApplyService;

@Service
public class ApplyServiceImpl implements ApplyService{
	
	@Autowired
	ApplyMapper applyMapper;
	
	@Autowired
	MediaService mediaService;
	
	@Override
	public String insertApply(MultipartFile[] applyFile, ApplyMemberVO vo) throws IllegalStateException, IOException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
		vo.setPassword(encoder.encode(vo.getPassword()));
		int result = applyMapper.insertApply(vo);
		if (applyFile != null) {
			MediaVO mvo = new MediaVO();
			mvo.setGroupId(vo.getApplyId());
			mvo.setGroups("회원신청");
			mvo.setSubGroup("가입신청이미지");
			mediaService.uploadMedia(applyFile, mvo);
		}
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

}
