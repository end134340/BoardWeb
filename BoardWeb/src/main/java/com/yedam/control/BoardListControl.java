package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.common.PageDTO;
import com.yedam.common.SearchDTO;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//페이징을 위한 파라미터
		String page = req.getParameter("page");
		//페이지 값이 없으면 (null이면) 자동으로 1로 하겠다는 뜻
		page = page == null ? "1" : page;
		String sc = req.getParameter("searchCondition");
		String kw = req.getParameter("keyword");
		
		// 글 목록 정보를 가지고 와서 jsp에 보여줌.
		SqlSession sqlSession = DataSource.getInstance().openSession(); // sql 세션을 하나 가져옴
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class); // 그냥 이렇게 쓰라고 했기 때문에 이 문법대로 사용해야함

		//페이징 계산
		
		SearchDTO search = new SearchDTO();
		search.setKeyword(kw);
		search.setPage(Integer.parseInt(page));
		search.setSearchCondition(sc);

		int totalCnt = mapper.selectTotal(search);
		PageDTO pageDTO = new PageDTO(totalCnt, Integer.parseInt(page));
		List<BoardVO> list = mapper.selectBoard(search);
		// 요청 정보에 무언가를 담아 전달할 때 setAttribute를 사용.
		req.setAttribute("blist", list); // list라는 값을 blist라는 이름으로 전달.
		req.setAttribute("paging", pageDTO);
		req.setAttribute("search", search);

		// boardList.do 를 호출하면 BoardListControl이 실행되어 결과를 jsp에 담아 그 jsp 페이지를 출력하고자 함
		// req.getRequestDispatcher: 요청(페이지)을 재지정하겠다는 기능을 가진 메소드. 다른 페이지를 열겠다는 뜼?
		req.getRequestDispatcher("/WEB-INF/views/boardList.jsp").forward(req, resp);
		// req: exec의 매개값
	}

}
