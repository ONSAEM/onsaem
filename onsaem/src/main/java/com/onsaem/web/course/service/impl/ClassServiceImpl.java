package com.onsaem.web.course.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.course.mapper.ClassMapper;
import com.onsaem.web.course.service.ClassService;
import com.onsaem.web.course.service.ClassInfoVO;

@Service
public class ClassServiceImpl implements ClassService{
	
	@Autowired
	ClassMapper classMapper;

	@Override
	public List<ClassInfoVO> getClassList(ClassInfoVO vo, Paging paging) {

		return classMapper.getClassList(vo);
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
	public Paging classCount(ClassInfoVO vo) {
		
		return classMapper.classCount(vo);
	}
	
	@Override
	public int classMaxPrice(ClassInfoVO vo) {
	
		return classMapper.classMaxPrice(vo);
	}

	@Override
	public int classMinPrice(ClassInfoVO vo) {
	
		return classMapper.classMinPrice(vo);
	}
	
	//[위는 완성 아래는 미완성]
	
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
