package com.onsaem.web.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.blog.mapper.BlogReplyMapper;
import com.onsaem.web.blog.service.BlogReplyService;
import com.onsaem.web.common.service.RepliesVO;

@Service
public class BlogReplyServiceImpl implements BlogReplyService {
	
	@Autowired BlogReplyMapper blogReplyMapper;
	
	@Override
	public List<RepliesVO> replyList(String groupId) {
		// 댓글 전체 조회
		return blogReplyMapper.replyList(groupId);
	}

	@Override
	public int replyInsert(RepliesVO repliesVO) {
		// 댓글 등록
		return blogReplyMapper.replyInsert(repliesVO);
	}

	@Override
	public RepliesVO replyCnt(RepliesVO repliesVO) {
		// 댓글 수 조회
		return blogReplyMapper.replyCnt(repliesVO);
	}

}
