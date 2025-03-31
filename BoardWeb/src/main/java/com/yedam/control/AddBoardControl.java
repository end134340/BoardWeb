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

public class AddBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		// 들어오는 요청에 따라 다른 코드 실행. getMethod(): 요청된 메소드 방식을 읽어오는 메소드
		if (req.getMethod().equals("GET")) {
			// get방식 요청시 요청 재지정(get: url에 직접 입력하거나 링크를 누르는 방식)
			req.getRequestDispatcher("/WEB-INF/views/addForm.jsp").forward(req, resp);
		} else if (req.getMethod().equals("POST")) {
			// post: form을 통해서???submit???하는게 아마 post?방식?

			// 등록
			String title = req.getParameter("title");
			String writer = req.getParameter("writer");
			String content = req.getParameter("content");

			BoardVO brd = new BoardVO();
			brd.setTitle(title);
			brd.setWriter(writer);
			brd.setContent(content);

			SqlSession sqlSession = DataSource.getInstance().openSession(true);
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			int r = mapper.insertBoard(brd);

			if (r > 0) {
				resp.sendRedirect("boardList.do"); // 요청 재지정이지만 전달할 파라미터(req, resp)가 없는 경우 사용.
			} else {
				System.out.println("오류가 발생했습니다.");
			}

		}
	}//

}
