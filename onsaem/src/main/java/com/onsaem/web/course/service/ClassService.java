package com.onsaem.web.course.service;

import java.util.List;

public interface ClassService {
	
	// 강의 단건조회
	public ClassVO getClass(ClassVO classVO);
	
	// 강의 전체조회
	public List<ClassVO> getClassList(ClassVO classVO);
	
	// 강의 등록
	public int classInsert(ClassVO classVO);
	
	//강의 수정
	public int classUpdate(ClassVO classVO);
}
