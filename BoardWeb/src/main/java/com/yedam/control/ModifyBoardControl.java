package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class ModifyBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정 처리 컨트롤... 수정 이후에 보드리스트로 가면 됨
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		String bno = req.getParameter("bno");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String page = req.getParameter("page");

		BoardVO brd = new BoardVO();
		brd.setBoardNo(Integer.parseInt(bno));
		brd.setTitle(title);
		brd.setContent(content);

		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

		int r = mapper.updateBoard(brd);
		if (r > 0) {
			resp.sendRedirect("boardList.do?page=" + page);
		} else {
			System.out.println("수정에 실패했습니다.");
		}

	}

}
