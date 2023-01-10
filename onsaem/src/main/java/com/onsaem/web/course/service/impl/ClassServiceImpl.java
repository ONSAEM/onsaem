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
	public List<ClassInfoVO> getClassList(ClassInfoVO classinfoVO) {

		return classMapper.getClassList(classinfoVO);
	}
	
	@Override
	public ClassInfoVO getClass(ClassInfoVO classinfoVO) {

		return classMapper.getClass(classinfoVO);
	}
	
	//[위는 완성 아래는 미완성]
	
	@Override
	public List<ClassInfoVO> popularClassList() {

		return null;
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
