package com.onsaem.web.course.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.QuestionVO;
import com.onsaem.web.common.service.ReportVO;
import com.onsaem.web.course.mapper.ClassMapper;
import com.onsaem.web.course.mapper.ClassQueMapper;
import com.onsaem.web.course.service.ClassService;
import com.onsaem.web.course.service.ClassVO;
import com.onsaem.web.member.service.MemberService;
import com.onsaem.web.course.service.ClassInfoVO;
import com.onsaem.web.course.service.ClassQueService;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	ClassMapper classMapper;

	@Autowired
	ClassQueMapper classQueMapper;

	@Autowired
	MemberService memberService;
	
	@Autowired
	MediaService mediaService;

	@Override
	public Map<String, Object> getClassInfoList(ClassInfoVO vo, Paging paging) {
		Paging newPaging = classMapper.classCount(vo);
		newPaging.setPageUnit(paging.getPageUnit());
		newPaging.setPage(paging.getPage());
		newPaging.setTotalRecord(newPaging.getTotalRecord());
		vo.setFirst(newPaging.getFirst());
		vo.setLast(newPaging.getLast());
		List<ClassInfoVO> list = classMapper.getClassInfoList(vo);
		for(ClassInfoVO info : list) {
			MediaVO media = new MediaVO();
			media.setGroupId(info.getClassId());
			info.setMedia(mediaService.getMedia(media));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("classList", list);
		result.put("newPaging", newPaging);
		return result;
	}

	@Override
	public ClassInfoVO getClassInfo(ClassInfoVO vo) {
		ClassInfoVO result = classMapper.getClassInfo(vo);
		QuestionVO qvo = new QuestionVO();
		qvo.setGroupId(vo.getClassId());
		result.setQueCount(classQueMapper.questionCount(qvo).getTotalRecord());
		result.setAddr(memberService.getMember(result.getMemberId()).getAddr());
		MediaVO media = new MediaVO();
		media.setGroupId(result.getClassId());
		result.setMediaList(mediaService.getMediaList(media));
		return result;
	}

	@Override
	public int classMaxPrice(ClassInfoVO vo) {

		return classMapper.classMaxPrice(vo);
	}

	@Override
	public int classMinPrice(ClassInfoVO vo) {

		return classMapper.classMinPrice(vo);
	}

	@Override
	public LikeVO LikeCount(String groupId, String memberId) {
		LikeVO result = new LikeVO();
		result.setGroupId(groupId);
		result.setCnt(classMapper.LikeCount(result));
		if (memberId != null) {
			result.setMemberId(memberId);
			result.setLikeCk(classMapper.LikeCheck(result));
		}
		return result;
	}

	@Override
	public int addClassLike(LikeVO vo) {
		classMapper.addClassLike(vo);
		return classMapper.LikeCount(vo);
	}

	@Override
	public int delClassLike(LikeVO vo) {
		classMapper.delClassLike(vo);
		return classMapper.LikeCount(vo);
	}
	

	@Override
	public boolean insertReport(ReportVO vo) {
		if (classMapper.insertReport(vo) > 0) {
			return true;
		} else {
			return false;

		}
	}

	@Override
	public ClassVO getClass(ClassVO vo) {
		ClassVO result = classMapper.getClass(vo);
		ClassInfoVO infoVo = new ClassInfoVO();
		infoVo.setClassId(result.getClassId());
		result.setClassInfo(classMapper.getClassInfo(infoVo));
		return result;
	}

	@Override
	public List<ClassVO> getDateList(ClassVO vo) {
	
		return classMapper.getDateList(vo);
	}

	@Override
	public List<ClassVO> getclassList(ClassVO vo) {
		
		return classMapper.getclassList(vo);
	}
	
	@Override
	public List<LikeVO> getLikeList(LikeVO vo) {
		List<LikeVO> list = classMapper.getLikeList(vo);
		for(LikeVO like : list) {
			ClassInfoVO cvo = new ClassInfoVO();
			cvo.setClassId(like.getGroupId());
			like.setClassInfo(classMapper.getClassInfo(cvo));
			MediaVO mvo = new MediaVO();
			mvo.setGroupId(like.getGroupId());
			like.getClassInfo().setMedia(mediaService.getMedia(mvo));
		}
		return list;
	}
	

	@Override
	public int delAllLike(LikeVO vo) {
		
		return classMapper.delAllLike(vo);
	}

	
	// [위는 완성 아래는 미완성]

	@Override
	public List<ClassInfoVO> popularClassList() {

		return null;
	}

	@Override
	public int classInsert(ClassInfoVO vo) {

		return classMapper.classInsert(vo);
	}

	@Override
	public int classUpdate(ClassInfoVO vo) {

		return classMapper.classUpdate(vo);
	}

	@Override
	public List<ClassInfoVO> getMyClassInfoList(ClassInfoVO vo) {
		List<ClassInfoVO> list = classMapper.getMyClassInfoList(vo);
		for(ClassInfoVO info : list) {
			MediaVO media = new MediaVO();
			media.setGroupId(info.getClassId());
			info.setMedia(mediaService.getMedia(media));
		}
		System.out.println(list);
		return list;
	}

	@Override
	public List<ClassInfoVO> getAdminClassList() {
		
		return classMapper.getAdminClassList();
	}

	@Override
	public List<ClassInfoVO> getAdminCApplyList() {
		
		return classMapper.getAdminCApplyList();
	}





}
