package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

@WebServlet("/addBoard") // 실행할 서블릿의 경로
public class AddBoardServ extends HttpServlet { // 서블릿이기 때문에 서블릿을 상속

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// addForm.jsp에서 넘긴 파라미터 값(title, writer, content)을 받아야함.
		// HttpServletRequest: 사용자가 넘긴 파라미터값을 읽어오기위해서???존재...
		// ?title=title&writer=user01&content=content ?뒤의 파라미터는 queryString이라고 함
		String title = req.getParameter("title"); // 저 파라미터의 값을 읽어 오기 위한 메소드.
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");

		BoardVO board = new BoardVO();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);

		// mybatis를 활용해 jdbc 처리
		SqlSession sqlSession = DataSource.getInstance().openSession(true); // 세션을 하나 받아오기 위해 DataSource의
																			// getInstance메소드를 이용
		// openSession을 할 때 true를 하면 자동커밋이 됨.
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class); // ~~.class하면...
		int r = mapper.insertBoard(board);
		resp.getWriter().print(r + "건 처리되었습니다.");
	}

} //
