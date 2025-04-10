package com.yedam.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.yedam.service.EventService;
import com.yedam.service.EventServiceImpl;

public class FilterOne implements Filter {
	// 클라이언트 - 필터 - 서블릿
	// 필터: 클래스. 인코딩 방식을 지정하거나...로그를 출력하거나 함. 필터링...?

	public FilterOne() {
		System.out.println("FilterOne 생성자.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("서블릿 실행 전");

		// 요청하고 있는 페이지와 클라이언트의 정보(ip)를 받아오려고 함
		String ip = request.getRemoteAddr();
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		String reqPage = uri.substring(context.length());

		// 로그 저장
		EventService svc = new EventServiceImpl();
		Map<String, String> map = new HashMap<String, String>();
		map.put("page", reqPage);
		map.put("host", ip);
		
		svc.logCreate(map);
		
		List<String> blockList = new ArrayList<>();
		blockList.add("192.168.0.33");
//		if(blockList.contains(ip)) {
//			return; //아이피 접속 불가로 만듦
//		}

		chain.doFilter(request, response);
		System.out.println("서블릿 실행 후");
	}

}
