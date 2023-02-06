package com.onsaem.web.course.service;

import java.util.List;
import java.util.Map;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.ReportVO;

public interface ClassService {

	// 클래스정보 전체조회
	public Map<String, Object> getClassInfoList(ClassInfoVO vo, Paging paging);

	// 클래스정보 단건조회
	public ClassInfoVO getClassInfo(ClassInfoVO vo);

	// 클래스 최대금액
	public int classMaxPrice(ClassInfoVO vo);

	// 클래스 최소금액
	public int classMinPrice(ClassInfoVO vo);

	// 클래스 좋아요 체크
	public LikeVO LikeCount(String groupId, String memberId);

	// 클래스 좋아요 추가
	public int addClassLike(LikeVO vo);

	// 클래스 좋아요 삭제
	public int delClassLike(LikeVO vo);

	// 클래스 신고
	public boolean insertReport(ReportVO vo);

	// 클래스 한건 가져오기
	public ClassVO getClass(ClassVO vo);

	// 클래스 날짜 목록 가져오기
	public List<ClassVO> getDateList(ClassVO vo);

	// 해당날짜 클래스 목록 가져오기
	public List<ClassVO> getclassList(ClassVO vo);

	// 좋아요 전체조회
	public List<LikeVO> getLikeList(LikeVO vo);

	// 좋아요 전체삭제
	public int delAllLike(LikeVO vo);

	// 강사클래스정보 전체조회
	public List<ClassInfoVO> getMyClassInfoList(ClassInfoVO vo);

	// 관리자 등록중인 클래스 전체조회
	public List<ClassInfoVO> getAdminClassList(ClassInfoVO vo);

	// 관리자 신청중인 클래스 전체조회
	public List<ClassInfoVO> getAdminCApplyList(ClassInfoVO vo);

	// 인기클래스목록조회
	public List<ClassInfoVO> popularClassList();
	
	// [위는 완성 아래는 미완성]

	// 클래스 등록
	public int classInsert(ClassInfoVO vo);

	// 클래스 수정
	public int classUpdate(ClassInfoVO vo);

}
