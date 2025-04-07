package com.yedam.mapper;

import java.util.List;

import com.yedam.vo.ReplyVO;

//댓글 목록, 등록, 삭제, 단건 조회
public interface ReplyMapper {
	//댓글 목록
	List<ReplyVO> selectList(int boardNo);
	
	//댓글 등록
	int insertReply(ReplyVO rvo);
	
	//댓글 삭제
	int deleteReply(int replyNo);
	
	//단건 조회
	ReplyVO selectReply(int replyNo);
	
	
}
