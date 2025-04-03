package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class LoginControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 처리
		// 전달되는 파라미터: uname(id), psw(pw) 가 있으면 보드리스트로, 없으면 다시 로그인하라고 함
		String id = req.getParameter("uname");
		String pw = req.getParameter("psw");

		// 컨트롤에서 직접 매퍼를 가지고 와서 처리하는 게 아니라 서비스 구현 객체를 가지고 와서 기능을 호출
		MemberService svc = new MemberServiceImpl();

		MemberVO mvo = svc.login(id, pw);

		if (mvo == null) {
			req.setAttribute("msg", "아이디와 비밀번호를 다시 확인해주세요.");
			req.getRequestDispatcher("common/loginForm.tiles").forward(req, resp);
		} else {
			// 로그인에 성공하면 세션 객체를 얻어와서 로그인 정보 저장.
			HttpSession session = req.getSession(); // 톰캣 서버의 세션 객체를 가져옴.
			// 이 세션은 사용자가 삭제(웹 브라우저를 닫거나, 로그아웃)하기 전까지는 값이 계속 유지가 됨.
			session.setAttribute("logId", id); // 세션 객체에 로그인한 정보를 attribute에 저장
			session.setAttribute("img", mvo.getImages());
			if (mvo.getResponsibility().equals("user")) {
//				resp.sendRedirect("main.do");
				req.getRequestDispatcher("common/main.tiles").forward(req, resp);

			} else if (mvo.getResponsibility().equals("admin")) {
				req.getRequestDispatcher("manager/main.tiles").forward(req, resp);
			}
		}
	}//

}
