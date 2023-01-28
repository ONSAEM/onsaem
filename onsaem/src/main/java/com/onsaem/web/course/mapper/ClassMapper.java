package com.onsaem.web.course.mapper;

import java.util.List;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.ReportVO;
import com.onsaem.web.course.service.ClassInfoVO;

public interface ClassMapper {

	// 강의 전체조회
	public List<ClassInfoVO> getClassList(ClassInfoVO vo);

	// 강의 단건조회
	public ClassInfoVO getClass(ClassInfoVO vo);

	// 강의 사진목록
	public List<MediaVO> classMediaList(ClassInfoVO vo);

	// 강의 갯수 조회
	public Paging classCount(ClassInfoVO vo);

	// 강의 최대금액
	public int classMaxPrice(ClassInfoVO vo);

	// 강의 최소금액
	public int classMinPrice(ClassInfoVO vo);

	// 강의 좋아요 갯수
	public int LikeCount(LikeVO vo);

	// 강의 좋아요 체크
	public String LikeCheck(LikeVO vo);

	// 강의 좋아요 추가
	public int addClassLike(LikeVO vo);

	// 강의 좋아요 삭제
	public int delClassLike(LikeVO vo);

	// [위는 완성 아래는 미완성]

	// 인기강의목록조회
	public List<ClassInfoVO> popularClassList();

	// 강의정보 등록
	public int classInfoInsert(ClassInfoVO vo);

	// 강의정보 수정
	public int classInfoUpdate(ClassInfoVO vo);

	// 강의 등록
	public int classInsert(ClassInfoVO vo);

	// 강의 수정
	public int classUpdate(ClassInfoVO vo);

	// 강의 신고
	public int insertReport(ReportVO vo);
}
