package com.onsaem.web.member.service.impl;

import java.io.IOException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.common.service.MailService;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.MailDTO;
import com.onsaem.web.member.mapper.MemberMapper;
import com.onsaem.web.member.service.MemberService;
import com.onsaem.web.member.service.MemberVO;

@Service
public class MemberServiceImpl implements MemberService, UserDetailsService {

	@Autowired
	MemberMapper memberMapper;

	@Autowired
	MailService mailService;

	@Autowired
	MediaService mediaService;

	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

		return memberMapper.getMember(memberId);
	}

	@Override
	public MemberVO checkId(String memberId) {

		return memberMapper.checkId(memberId);
	}

	@Override
	public String searchEmail(String email) {
		if (memberMapper.searchEmail(email) == 0) {
			return "false";
		} else {
			return "true";
		}
	}

	@Override
	public String sendAuthMail(String email) {
		MailDTO dto = new MailDTO();
		String[] emList = new String[1];
		emList[0] = email;
		dto.setAddress(emList);
		dto.setSubject("[온샘] 이메일 인증번호 발급");
		dto.setTitle("회원가입 이메일 인증");
		dto.setFrom("onseam");
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		dto.setContent("인증번호 : " + generatedString);

		if (mailService.sendMail(dto, null)) {
			return generatedString;
		} else {
			return "fail";
		}
	}

	@Override
	public MemberVO getMember(String memberId) {

		return memberMapper.getMember(memberId);
	}

	@Override
	public String insertMember(MultipartFile[] profileFile, MemberVO member) throws IllegalStateException, IOException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
		member.setPassword(encoder.encode(member.getPassword()));
		if (member.getBank() == null || member.getBankAccount() == null) {
			member.setBank(null);
			member.setBankAccount(null);
		}
		if (member.getNickname() == null) {
			member.setNickname(member.getMemberId());
		}
		int result = memberMapper.insertMember(member);
		if (profileFile != null) {
			MediaVO vo = new MediaVO();
			vo.setGroupId(member.getMemberId());
			vo.setGroups("회원");
			vo.setSubGroup("프로필이미지");
			mediaService.uploadMedia(profileFile, vo);
		}
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	@Override
	public String sendIdMail(String email) {
		MailDTO dto = new MailDTO();
		String[] emList = new String[1];
		emList[0] = email;
		dto.setAddress(emList);
		dto.setSubject("[온샘] 아이디 찾기 관련");
		dto.setTitle("아이디 정보");
		dto.setFrom("onseam");
		MemberVO vo = memberMapper.idEmail(email);
		dto.setContent("회원 아이디는 " + vo.getMemberId() + " 입니다");

		if (mailService.sendMail(dto, null)) {
			return "seccss";
		} else {
			return "fail";
		}
	}

	@Override
	public String updatePw(MemberVO member) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
		member.setPassword(encoder.encode(member.getPassword()));
		if (memberMapper.updatePw(member) > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	@Override
	public String updateMember(MultipartFile[] profileFile, MemberVO member) throws IllegalStateException, IOException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
		member.setPassword(encoder.encode(member.getPassword()));
		if (member.getBank() == null || member.getBankAccount() == null) {
			member.setBank(null);
			member.setBankAccount(null);
		}
		if (member.getNickname() == null) {
			member.setNickname(member.getMemberId());
		}
		int result = memberMapper.insertMember(member);
		if (profileFile != null) {
			MediaVO vo = new MediaVO();
			vo.setGroupId(member.getMemberId());
			vo.setGroups("회원");
			vo.setSubGroup("프로필이미지");
			mediaService.stopMedia(vo);
			mediaService.uploadMedia(profileFile, vo);
		}
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

}
