package com.onsaem.web.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.course.mapper.ClassMapper;
import com.onsaem.web.course.service.ClassService;
import com.onsaem.web.course.service.ClassInfoVO;

@Service
public class ClassServiceImpl implements ClassService{
	
	@Autowired
	ClassMapper classMapper;

	@Override
	public List<ClassInfoVO> getClassList(ClassInfoVO vo) {

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
