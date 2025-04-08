package com.yedam.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.common.SearchDTO;
import com.yedam.vo.ReplyVO;

//댓글 목록, 등록, 삭제, 단건 조회
public interface ReplyMapper {
	//댓글 목록
	List<ReplyVO> selectList(SearchDTO search);
	
	//댓글 등록
	int insertReply(ReplyVO rvo);
	
	//댓글 삭제
	int deleteReply(int replyNo);
	
	//단건 조회
	ReplyVO selectReply(int replyNo);
	
	//건수 조회
	int selectReplyCnt(int boardNo);
	
	//datatable용도
	List<Map<String, Object>> selectListForDT(int boardNo);
	
}
