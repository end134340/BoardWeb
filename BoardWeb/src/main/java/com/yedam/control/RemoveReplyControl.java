package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;

public class RemoveReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터로 댓글 번호인 rno를 받음
		String rno = req.getParameter("rno");

		ReplyService svc = new ReplyServiceImpl();

		if (svc.removeReply(Integer.parseInt(rno))) {
			//삭제 성공시: {"retCode": "OK"} / 삭제 실패시: {"retCode": "NG"}라는 값을 넘김.
			resp.getWriter().print("{\"retCode\": \"OK\"}");
		} else {
			resp.getWriter().print("{\"retCode\": \"NG\"}");
		}
	}

}
