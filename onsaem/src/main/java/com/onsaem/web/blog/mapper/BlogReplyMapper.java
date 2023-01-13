package com.onsaem.web.blog.mapper;

import java.util.List;

import com.onsaem.web.blog.service.BlogReplyVO;
import com.onsaem.web.blog.service.BlogReportVO;
import com.onsaem.web.common.service.RepliesVO;

public interface BlogReplyMapper {
	// 댓글 전체 조회
		List<RepliesVO> replyList(String groupId);
	// 댓글 작성
		int replyInsert(RepliesVO repliesVO);
	// 댓글 수 조회
		public RepliesVO replyCnt(RepliesVO repliesVO);
	
	

}
