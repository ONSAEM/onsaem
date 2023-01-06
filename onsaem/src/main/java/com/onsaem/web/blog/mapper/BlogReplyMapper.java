package com.onsaem.web.blog.mapper;

import java.util.List;

import com.onsaem.web.blog.service.BlogReplyVO;
import com.onsaem.web.blog.service.BlogReportVO;

public interface BlogReplyMapper {
	// 댓글 작성
	int replyInsert(BlogReplyVO replyVO);
	// 댓글 수정
	int replyUpdate(BlogReplyVO replyVO);
	// 댓글 삭제
	int replyDelete(BlogReplyVO replyVO);
	// 댓글 전체 조회
	List<BlogReplyVO> replyList(BlogReplyVO replyVO);
	// 댓글 신고
	int reportInsert(BlogReportVO reportVO);
}
