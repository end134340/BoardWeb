package com.yedam.service;

import java.util.List;
import java.util.Map;

import com.yedam.common.SearchDTO;
import com.yedam.vo.ReplyVO;

public interface ReplyService {
	// 업무 프로세스이기 때문에 쿼리와 다른 용어 사용

	List<ReplyVO> replyList(SearchDTO search);
	
	boolean addReply(ReplyVO reply);
	
	boolean removeReply(int replyNo);
	
	ReplyVO getReply(int replyNo);
	
	//페이징 계산
	int getTotalCnt(int boardNo);
	
	//datatable용 데이터
	List<Map<String, Object>> replyListForDT(int boardNo);
}
