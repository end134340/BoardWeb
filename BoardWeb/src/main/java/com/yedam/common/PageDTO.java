package com.yedam.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	// 페이징을 위해 게시글 건수 계산해주는 클래스(게시글 건수에 따른 페이지 수 계산)

	private int startPage; // 현재 보여주는 페이지를 기준으로 맨 첫번째? 페이지?
	private int endPage; // 현재 보여주는 페이지를 기준으로 맨 마지막 페이지?
	private int currentPage; // 현재 페이지
	private boolean prev, next; // 이전, 이후 10개 페이지가 있는지 여부

	// 생성자
	public PageDTO(int totalCnt, int page) {
		currentPage = page;
		endPage = (int) (Math.ceil(page / 10.0)) * 10;
		startPage = endPage - 9;
		int realEnd = (int) (Math.ceil(totalCnt / 10.0));

		// 만약 계산상의 마지막 페이지보다 endPage보다 크다면(페이지가 더 있다면) realEnd로 마지막 페이지 값을 바꿈.
		endPage = endPage > realEnd ? realEnd : endPage;

		// 이전, 이후 페이지(10개 페이지)가 있는지 유무 계산
		prev = startPage > 1 ? true : false;
		next = endPage < realEnd ? true : false;
	}
}
