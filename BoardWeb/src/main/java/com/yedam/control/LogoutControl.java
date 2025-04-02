package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;

public class LogoutControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션을 지워주기만 하면 됨
		HttpSession session = req.getSession();
		session.invalidate(); // 세션 정보를 삭제하는 메소드.
		
		resp.sendRedirect("loginForm.do");
	}

}
