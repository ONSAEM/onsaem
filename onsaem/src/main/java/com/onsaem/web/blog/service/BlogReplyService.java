package com.onsaem.web.blog.service;

import java.util.List;

import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.RepliesVO;

public interface BlogReplyService {
	// 댓글 전체 조회
		List<RepliesVO> replyList(String groupId);
	// 댓글 등록
		int replyInsert(RepliesVO repliesVO);
	// 댓글 수 조회
		public RepliesVO replyCnt(RepliesVO repliesVO);
	// 댓글 삭제
		public int replyDel(String replyId);
}
