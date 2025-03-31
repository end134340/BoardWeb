package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.control.AddBoardControl;
import com.yedam.control.BoardControl;
import com.yedam.control.BoardListControl;

// *.do 로 끝나는 요청을 실행
public class FrontController extends HttpServlet {

	// 요청 url과 실행 컨트롤 연결?등록?
	Map<String, Control> map;

	// 생성자
	public FrontController() {
		map = new HashMap<String, Control>();
	}

	// init()
	@Override
	public void init(ServletConfig config) throws ServletException {
		// /*.do라는 요청이 들어오면, Control인터페이스를 구현하는 클래스를 호출?하겠다?
		//상세 화면
		map.put("/board.do", new BoardControl());
		//글 목록
		map.put("/boardList.do", new BoardListControl());
		//글 등록 페이지 호출
		map.put("/addBoard.do", new AddBoardControl()); //addForm.jsp라는 페이지가 열리면 됨
	}

	// service()
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//url과 uri의 차이: 
		//http://localhost:8080/BoardWeb/board.do = url 
		//BoardWeb/board.do = uri
		System.out.println("서비스 메소드 실행.");
		String uri = req.getRequestURI();
		System.out.println("요청 URI: " + uri); //BoardWeb/board.do

		// 프로젝트 네임 가지고오는 메소드
		String context = req.getContextPath();
		//프로젝트 네임 다음부터 자르면 요청 페이지 정보가 됨
		String page = uri.substring(context.length()); // "/board.do/"
		System.out.println("page: " + page);

		//키(url) => 컨트롤 반환
		Control sub = map.get(page); //map의 key를 넣어줌
		sub.exec(req, resp);
	}
}
