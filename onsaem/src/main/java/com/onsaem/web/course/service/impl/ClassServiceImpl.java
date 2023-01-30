package com.onsaem.web.course.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.QuestionVO;
import com.onsaem.web.common.service.ReportVO;
import com.onsaem.web.course.mapper.ClassMapper;
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
	ClassQueService classQueService;

	@Autowired
	MemberService memberService;

	@Override
	public Map<String, Object> getClassInfoList(ClassInfoVO vo, Paging paging) {
		Paging newPaging = classMapper.classCount(vo);
		newPaging.setPageUnit(paging.getPageUnit());
		newPaging.setPage(paging.getPage());
		newPaging.setTotalRecord(newPaging.getTotalRecord());
		vo.setFirst(newPaging.getFirst());
		vo.setLast(newPaging.getLast());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("classList", classMapper.getClassInfoList(vo));
		result.put("newPaging", newPaging);
		return result;
	}

	@Override
	public ClassInfoVO getClassInfo(ClassInfoVO vo) {
		ClassInfoVO result = classMapper.getClassInfo(vo);
		QuestionVO qvo = new QuestionVO();
		qvo.setGroupId(vo.getClassId());
		result.setQueCount(classQueService.questionCount(qvo));
		result.setAddr(memberService.getMember(result.getMemberId()).getAddr());
		return result;
	}

	@Override
	public List<MediaVO> classMediaList(ClassInfoVO vo) {

		return classMapper.classMediaList(vo);
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
	public ClassVO getClass(ClassVO vo) {
		ClassVO result = classMapper.getClass(vo);
		ClassInfoVO infoVo = new ClassInfoVO();
		infoVo.setClassId(result.getClassId());
		result.setClassInfo(classMapper.getClassInfo(infoVo));
		return result;
	}

	@Override
	public List<ClassVO> getclassList(ClassVO vo) {
		
		return classMapper.getclassList(vo);
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
	public boolean insertReport(ReportVO vo) {
		if (classMapper.insertReport(vo) > 0) {
			return true;
		} else {
			return false;

		}
	}

}
