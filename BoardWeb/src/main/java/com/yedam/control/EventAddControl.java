package com.yedam.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.EventService;
import com.yedam.service.EventServiceImpl;
import com.yedam.vo.EventVO;

public class EventAddControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json; charset=UTF-8");

		String title = req.getParameter("title");
		String start = req.getParameter("start");
		String end = req.getParameter("end");

		EventVO vo = new EventVO();

		vo.setTitle(title);
		vo.setStart(start);
		vo.setEnd(end);

		EventService svc = new EventServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().create();

		if (svc.addEvent(vo)) {
			map.put("retCode", "OK");
		} else {
			map.put("retCode", "NG");
		}

		String json = gson.toJson(map);
		resp.getWriter().print(json);

	}

}
