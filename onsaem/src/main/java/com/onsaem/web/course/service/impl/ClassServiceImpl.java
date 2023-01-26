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
import com.onsaem.web.course.mapper.ClassMapper;
import com.onsaem.web.course.service.ClassService;
import com.onsaem.web.course.service.ClassInfoVO;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	ClassMapper classMapper;

	@Override
	public Map<String, Object> getClassList(ClassInfoVO vo, Paging paging) {
		Paging newPaging = classMapper.classCount(vo);
		newPaging.setPageUnit(paging.getPageUnit());
		newPaging.setPage(paging.getPage());
		newPaging.setTotalRecord(newPaging.getTotalRecord());
		vo.setFirst(newPaging.getFirst());
		vo.setLast(newPaging.getLast());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("classList", classMapper.getClassList(vo));
		result.put("newPaging", newPaging);
		return result;
	}

	@Override
	public ClassInfoVO getClass(ClassInfoVO vo) {

		return classMapper.getClass(vo);
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
	public LikeVO LikeCount(String groupId) {
		LikeVO result = new LikeVO();
		result.setGroupId(groupId);
		result.setCnt(classMapper.LikeCount(result));
		result.setLikeCk(classMapper.LikeCheck(result));
		return result;
	}

	@Override
	public int addClassLike(LikeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delClassLike(LikeVO vo) {
		// TODO Auto-generated method stub
		return 0;
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

}
