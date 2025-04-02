package com.yedam.mapper;

import java.util.List;

import com.yedam.common.SearchDTO;
import com.yedam.vo.BoardVO;

public interface BoardMapper {
//목록(id가 메서드 이름, parameter 매개값)
	List<BoardVO> selectBoard(SearchDTO dto);

	// 등록
	int insertBoard(BoardVO board);

	// 수정
	int updateBoard(BoardVO board);

	// 삭제
	int deleteBoard(int boardNo);

	// 상세 조회
	BoardVO selectOneBoard(int boardNo);
	
	int selectTotal(SearchDTO search);
	
	
}
