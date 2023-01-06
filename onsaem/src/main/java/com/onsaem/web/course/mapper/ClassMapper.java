package com.onsaem.web.course.mapper;

import java.util.List;

import com.onsaem.web.course.service.ClassVO;

public interface ClassMapper {
	
	// 강의 단건조회
	public ClassVO getClass(ClassVO classVO);
	
	// 강의 전체조회
	public List<ClassVO> getClassList(ClassVO classVO);
	
	// 강의정보 등록
	public int classInfoInsert(ClassVO classVO);
	
	// 강의정보 수정
	public int classInfoUpdate(ClassVO classVO);
	
	// 강의 등록
	public int classInsert(ClassVO classVO);
	
	//강의 수정
	public int classUpdate(ClassVO classVO);
}
