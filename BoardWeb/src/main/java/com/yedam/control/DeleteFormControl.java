package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class DeleteFormControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 삭제 화면(deleteForm.jsp) 열어줌
		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

		String bno = req.getParameter("bno");
		String page = req.getParameter("page");

		BoardVO board = mapper.selectOneBoard(Integer.parseInt(bno));
		req.setAttribute("brd", board);
		req.setAttribute("page", Integer.parseInt(page));

		HttpSession session = req.getSession();
		String logId = (String) session.getAttribute("logId");

		if (logId != null && logId.equals(board.getWriter())) {
			req.getRequestDispatcher("/WEB-INF/views/deleteForm.jsp").forward(req, resp);
		}else {
			req.setAttribute("msg", "삭제할 권한이 없습니다.");
			req.getRequestDispatcher("WEB-INF/views/board.jsp").forward(req, resp);
			
		}

	}

}
