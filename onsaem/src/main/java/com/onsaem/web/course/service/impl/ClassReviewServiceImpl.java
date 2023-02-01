package com.onsaem.web.course.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.ReviewVO;
import com.onsaem.web.course.mapper.ClassReviewMapper;
import com.onsaem.web.course.service.ClassReviewService;
import com.onsaem.web.member.service.MemberService;

@Service
public class ClassReviewServiceImpl implements ClassReviewService {

	@Autowired
	ClassReviewMapper classReviewMapper;
	
	@Autowired
	MediaService mediaService;
	
	@Autowired
	MemberService memberService;

	@Override
	public Map<String, Object> getReviewList(ReviewVO rvo, Paging paging) {
		Paging newPaging = classReviewMapper.reviewCount(rvo);
		newPaging.setPage(paging.getPage());
		newPaging.setTotalRecord(newPaging.getTotalRecord());
		rvo.setFirst(newPaging.getFirst());
		rvo.setLast(newPaging.getLast());
		List<ReviewVO> reviewList = classReviewMapper.getReviewList(rvo);
		for(ReviewVO review : reviewList) {
			MediaVO mvo = new MediaVO();
			mvo.setMediaOrder(0);
			mvo.setGroups("강의");
			mvo.setGroupId(review.getReviewId());
			review.setReviewMedia(mediaService.getMedia(mvo));
			mvo.setGroups("회원");
			mvo.setGroupId(review.getWriterId());
			review.setProfile(mediaService.getMedia(mvo));
			review.setNickname(memberService.getMember(review.getWriterId()).getNickname());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rList", reviewList);
		result.put("rPaging", newPaging);
		return result;
	}

	@Override
	public ReviewVO getReview(ReviewVO vo) {
		ReviewVO review = classReviewMapper.getReview(vo);
		MediaVO mvo = new MediaVO();
		mvo.setMediaOrder(0);
		mvo.setGroups("강의");
		mvo.setGroupId(review.getReviewId());
		review.setReviewMediaList(mediaService.getMediaList(mvo));
		mvo.setGroups("회원");
		mvo.setGroupId(review.getWriterId());
		review.setProfile(mediaService.getMedia(mvo));
		review.setNickname(memberService.getMember(review.getWriterId()).getNickname());
		return review;
	}

	@Override
	public int reviewCount(ReviewVO vo) {
		
		return classReviewMapper.reviewCount(vo).getTotalRecord();
	}

	@Override
	public int starAvg(ReviewVO vo) {
		
		return classReviewMapper.starAvg(vo);
	}

	@Override
	public ReviewVO insertReview(ReviewVO vo) {
		if(classReviewMapper.insertReview(vo)>0) {
			return vo;
		}else {
			return null;
		}
	}

	@Override
	public boolean updateReview(ReviewVO vo) {
		if(classReviewMapper.updateReview(vo)>0) {
			return true;
		}else {
			return false;
		}
	}

}
