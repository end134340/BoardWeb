package com.yedam.service;

import java.util.List;

import com.yedam.vo.ReplyVO;

public interface ReplyService {
	// 업무 프로세스이기 때문에 쿼리와 다른 용어 사용

	List<ReplyVO> replyList(int boardNo);
	
	boolean addReply(ReplyVO reply);
	
	boolean removeReply(int replyNo);
	
	ReplyVO getReply(int replyNo);
}
