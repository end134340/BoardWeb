package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.SearchDTO;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class ReplyListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=UTF-8");

		// 글 번호에 대한 댓글 데이터를 json 형태로 만듦

		String bno = req.getParameter("bno");
		String page = req.getParameter("page"); //댓글 페이지네이션을 하기 위해서 페이지를 받음
		
		// 서비스에 등록된 업무 로직 호출
		ReplyService svc = new ReplyServiceImpl();
		SearchDTO search = new SearchDTO();
		search.setBoardNo(Integer.parseInt(bno));
		search.setPage(Integer.parseInt(page));

		List<ReplyVO> list = svc.replyList(search);
		// Json형태의 문자열: [{"replyNo": 10, "reply": "댓글" … }]

		String json = "[";
		for (int i = 0; i < list.size(); i++) {
			json += "{\"replyNo\": " + list.get(i).getReplyNo() //
					+ ", \"reply\": \"" + list.get(i).getReply() + "\"" //
					+ ", \"replyer\": \"" + list.get(i).getReplyer() + "\"" //
					+ ", \"reply_date\": \"" + list.get(i).getReplyDate() + "\"" //
					+ ", \"board_no\": " + list.get(i).getBoardNo() + "}";
			if (i + 1 != list.size()) {
				json += ", ";
			}
		}
		json += "]";
//		Gson gson = new GsonBuilder().create();
//		json = gson.toJson(list);
		resp.getWriter().print(json);
	}//

}
