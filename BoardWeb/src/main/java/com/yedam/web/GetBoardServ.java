package com.yedam.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

@WebServlet("/getBoard")
public class GetBoardServ extends HttpServlet {
	// http://localhost/BoardWeb/getBoard?board_no=13

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		resp.setContentType("text/html;charset=utf-8");
		String no = req.getParameter("board_no");

		BoardVO brd = mapper.selectOneBoard(Integer.parseInt(no));
		PrintWriter out = resp.getWriter();

		String html = "<table border='1'>";
		html += "<tr>";
		html += "<th>no</th>";
		html += "<td>" + brd.getBoardNo() + "</td>";
		html += "<th>제목</th>";
		html += "<td>" + brd.getTitle() + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>작성자</th>";
		html += "<td>" + brd.getWriter() + "</td>";
		html += "<th>작성일시</th>";
		html += "<td>" + brd.getWriteDate() + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>내용</th>";
		html += "<td colspan='3'>" + brd.getContent() + "</td>";
		html += "</tr>";
		html += "<p><a href='mainservlet'>목록으로</a></p>";
		out.print(html);
	}
}
