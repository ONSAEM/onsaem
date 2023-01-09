package com.onsaem.web.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.course.mapper.ClassMapper;
import com.onsaem.web.course.service.ClassService;
import com.onsaem.web.course.service.ClassInfoVO;

@Service
public class ClassServiceImpl implements ClassService{
	
	@Autowired
	ClassMapper classMapper;

	@Override
	public ClassInfoVO getClass(ClassInfoVO classinfoVO) {

		return classMapper.getClass(classinfoVO);
	}

	@Override
	public List<ClassInfoVO> getClassList(ClassInfoVO classinfoVO) {

		return classMapper.getClassList(classinfoVO);
	}

	@Override
	public int classInsert(ClassInfoVO classinfoVO) {

		return classMapper.classInsert(classinfoVO);
	}

	@Override
	public int classUpdate(ClassInfoVO classinfoVO) {

		return classMapper.classUpdate(classinfoVO);
	}
	
	
}
