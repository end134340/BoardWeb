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
import com.yedam.control.AddReplyControl;
import com.yedam.control.BoardControl;
import com.yedam.control.BoardListControl;
import com.yedam.control.DeleteBoardControl;
import com.yedam.control.DeleteFormControl;
import com.yedam.control.EventAddControl;
import com.yedam.control.EventFormControl;
import com.yedam.control.EventListControl;
import com.yedam.control.EventRemoveControl;
import com.yedam.control.JSControl;
import com.yedam.control.LoginControl;
import com.yedam.control.LoginFormControl;
import com.yedam.control.LogoutControl;
import com.yedam.control.MainControl;
import com.yedam.control.ModifyBoardControl;
import com.yedam.control.ModifyFormControl;
import com.yedam.control.RLDatatableControl;
import com.yedam.control.RemoveReplyControl;
import com.yedam.control.ReplyCountControl;
import com.yedam.control.ReplyListControl;
import com.yedam.control.SignUpControl;

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
		//메인 화면
		map.put("/main.do", new MainControl());
		
		//상세 화면
		map.put("/board.do", new BoardControl());
		//글 목록
		map.put("/boardList.do", new BoardListControl());
		//글 등록 페이지 호출
		map.put("/addBoard.do", new AddBoardControl()); //addForm.jsp라는 페이지가 열리면 됨
		//수정 화면 페이지 호출
		map.put("/modifyForm.do", new ModifyFormControl());
		//실제로 수정 처리
		map.put("/modifyBoard.do", new ModifyBoardControl());
		//삭제 페이지 호출
		map.put("/deleteForm.do", new DeleteFormControl());
		//삭제 처리
		map.put("/deleteBoard.do", new DeleteBoardControl());
		//로그인 페이지로 연결
		map.put("/loginForm.do", new LoginFormControl());
		//로그인 처리
		map.put("/login.do", new LoginControl());
		//로그아웃
		map.put("/logout.do", new LogoutControl());
		//회원가입 처리
		map.put("/signForm.do", new SignUpControl());
		map.put("/signup.do", new SignUpControl());
		
		//자바스크립트 연습
		map.put("/javascript.do", new JSControl());
		
		//댓글 관련
		//댓글 리스트
		map.put("/replyList.do", new ReplyListControl());
		//삭제
		map.put("/removeReply.do", new RemoveReplyControl());
		//등록
		map.put("/addReply.do", new AddReplyControl());
		//페이지 수 조회
		map.put("/replyCount.do", new ReplyCountControl());
		
		//datatable을 이용해서 데이터 출력
		map.put("/replyListDatatable.do", new RLDatatableControl());
		
		//Fullcalendar 관련
		map.put("/eventForm.do", new EventFormControl());
		//일정 등록
		map.put("/addEvent.do", new EventAddControl());
		//일정 삭제
		map.put("/removeEvent.do", new EventRemoveControl());
		//전체 일정
		//eventList.do => [{}, {}]
		map.put("/eventList.do", new EventListControl());
		
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
