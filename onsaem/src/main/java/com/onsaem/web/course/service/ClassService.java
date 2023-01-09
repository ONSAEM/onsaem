package com.onsaem.web.course.service;

import java.util.List;

public interface ClassService {
	
	// 강의 단건조회
	public ClassInfoVO getClass(ClassInfoVO classinfoVO);
	
	// 강의 전체조회
	public List<ClassInfoVO> getClassList(ClassInfoVO classinfoVO);
	
	// 강의 등록
	public int classInsert(ClassInfoVO classinfoVO);
	
	//강의 수정
	public int classUpdate(ClassInfoVO classinfoVO);
}
