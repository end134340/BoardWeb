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

public class ModifyFormControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터로 들어온 값을 읽어들여 단건조회하고 페이지(modifyBoard.jsp)에 정보 전달.
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		String no = req.getParameter("bno");
		String page = req.getParameter("page");

		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		BoardVO board = mapper.selectOneBoard(Integer.parseInt(no));

		// 권한이 있는지 체크
		HttpSession session = req.getSession();
		String logId = (String) session.getAttribute("logId");

		req.setAttribute("brd", board);
		req.setAttribute("page", Integer.parseInt(page));
		
		
		//
		if (logId != null && logId.equals(board.getWriter())) {

			req.getRequestDispatcher("common/modifyBoard.tiles").forward(req, resp);
		} else {
			req.setAttribute("msg", "수정할 권한이 없습니다.");
			req.getRequestDispatcher("common/board.tiles").forward(req, resp);
		}

	}

}
