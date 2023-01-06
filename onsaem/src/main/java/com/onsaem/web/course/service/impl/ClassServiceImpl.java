package com.onsaem.web.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.course.mapper.ClassMapper;
import com.onsaem.web.course.service.ClassService;
import com.onsaem.web.course.service.ClassVO;

@Service
public class ClassServiceImpl implements ClassService{
	
	@Autowired
	ClassMapper classMapper;

	@Override
	public ClassVO getClass(ClassVO classVO) {

		return classMapper.getClass(classVO);
	}

	@Override
	public List<ClassVO> getClassList(ClassVO classVO) {

		return classMapper.getClassList(classVO);
	}

	@Override
	public int classInsert(ClassVO classVO) {

		return classMapper.classInsert(classVO);
	}

	@Override
	public int classUpdate(ClassVO classVO) {

		return classMapper.classUpdate(classVO);
	}
	
	
}
