package com.yedam.service;

import java.util.List;
import java.util.Map;

import com.yedam.vo.EventVO;

public interface EventService {
	// 이벤트 등록
	boolean addEvent(EventVO event);

	// 이벤트 삭제
	boolean removeEvent(String title);

	// 이벤트 전체출력
	List<EventVO> eventList();

	// 차트
	List<Map<String, Object>> cntPerWriter();

	// 로그 출력
	void logCreate(Map<String, String> map);
}
