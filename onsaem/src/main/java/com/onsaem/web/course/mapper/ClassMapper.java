package com.onsaem.web.course.mapper;

import java.util.List;

import com.onsaem.web.course.service.ClassInfoVO;

public interface ClassMapper {
	
	// 강의 전체조회
	public List<ClassInfoVO> getClassList(ClassInfoVO classinfoVO);
	// 강의 단건조회
	public ClassInfoVO getClass(ClassInfoVO classinfoVO);
	
	//[위는 완성 아래는 미완성]

	// 인기강의목록조회
	public List<ClassInfoVO> popularClassList();
	
	// 강의정보 등록
	public int classInfoInsert(ClassInfoVO classinfoVO);
	
	// 강의정보 수정
	public int classInfoUpdate(ClassInfoVO classinfoVO);
	
	// 강의 등록
	public int classInsert(ClassInfoVO classinfoVO);
	
	//강의 수정
	public int classUpdate(ClassInfoVO classinfoVO);
}
