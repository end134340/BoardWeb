package com.yedam.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.vo.EventVO;

public interface EventMapper {
	//이벤트 등록
	int insertEvent(EventVO event);
	
	//이벤트 삭제
	int deleteEvent(String title);
	
	//이벤트 전체 조회
	List<EventVO> eventList();
	
	//차트
	List<Map<String, Object>> selectWriter();
	
	//로그(필터)
	int insertLogging(Map<String, String> map);
}
