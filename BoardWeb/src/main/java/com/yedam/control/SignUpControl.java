package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class SignUpControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 회원가입 화면(get방식)
		if (req.getMethod().equals("GET")) {
			req.getRequestDispatcher("common/signForm.tiles").forward(req, resp);
		} else if (req.getMethod().equals("POST")) {
			// 회원가입 처리(post방식) (multipart요청이 들어오면 파일 업로드: images, db등록)
			// COS라이브러리를 추가해 가져옴
			// 1) 요청정보 2)경로 3)파일 최대크기 제한 4)인코딩 방식(파일에 한글 등이 들어가있는 경우) 5)리네임 정책(동일한 이름의 파일이
			// 있으면 이름을 바꾸는 등의 처리)값이 필요함.
			String saveDir = req.getServletContext().getRealPath("images"); // 이 경로에다가 파일을 만들겠다는 뜼?
			int maxSize = 1024 * 1024 * 5; // 1024 * 1024 = 1mb. (5MB)
			String enc = "UTF-8"; // 인코딩 방식
			MultipartRequest mr = new MultipartRequest(req, saveDir, maxSize, enc, new DefaultFileRenamePolicy());

			// 멀티파트 요청인 경우 req.getParameter를 사용하지 못하고 아래의 방식을 사용해야함.
			String uid = mr.getParameter("userId");
			String upw = mr.getParameter("userPw");
			String uname = mr.getParameter("userName");
			// 동일한 이름이 있는 경우 바뀐 이름으로 가져와야하기 때문에 이 메소드 사용.
			String uimg = mr.getFilesystemName("userImg");
			MemberVO mem = new MemberVO();

			mem.setUserId(uid);
			mem.setPassword(upw);
			mem.setUserName(uname);
			mem.setImages(uimg);

			MemberService msv = new MemberServiceImpl();
			if(msv.addMember(mem)) {
				resp.sendRedirect("loginForm.do");
			}else {
				System.out.println("회원가입에 실패했습니다.");
			}

		}
	}

}
