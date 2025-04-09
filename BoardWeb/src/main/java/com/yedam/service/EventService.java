package com.yedam.service;

import java.util.List;

import com.yedam.vo.EventVO;

public interface EventService {
	//이벤트 등록
	boolean addEvent(EventVO event);
	
	//이벤트 삭제 
	boolean removeEvent(String title);
	
	//이벤트 전체출력
	List<EventVO> eventList();
}
