package com.onsaem.web.course.service;

import java.util.List;
import java.util.Map;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.ReportVO;

public interface ClassService {
	
	// 강의정보 전체조회
	public Map<String, Object> getClassInfoList(ClassInfoVO vo, Paging paging);

	// 강의정보 단건조회
	public ClassInfoVO getClassInfo(ClassInfoVO vo);
	
	// 강의 사진목록
	public List<MediaVO> classMediaList(ClassInfoVO vo);
	
	// 강의 최대금액
	public int classMaxPrice(ClassInfoVO vo);
	
	// 강의 최소금액
	public int classMinPrice(ClassInfoVO vo);
	
	// 강의 좋아요 체크
	public LikeVO LikeCount(String groupId, String memberId);
	
	// 강의 좋아요 추가
	public int addClassLike(LikeVO vo);
	
	// 강의 좋아요 삭제
	public int delClassLike(LikeVO vo);
	
	// 강의 신고
	public boolean insertReport(ReportVO vo);
	
	// 강의 한건 가져오기
	public ClassVO getClass(ClassVO vo);
	
	// 강의 목록 가져오기
	public List<ClassVO> getclassList(ClassVO vo);
	
	//[위는 완성 아래는 미완성]
	
	// 인기강의목록조회
	public List<ClassInfoVO> popularClassList();
	
	// 강의 등록
	public int classInsert(ClassInfoVO vo);
	
	//강의 수정
	public int classUpdate(ClassInfoVO vo);
	
}
