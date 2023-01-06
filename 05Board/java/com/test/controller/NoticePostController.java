package com.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoticePostController implements SubController {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// 1 파라미터

		// 2 Validation

		// 3 Service

		// 4 View
		try {
			req.getRequestDispatcher("/WEB-INF/view/notice/post.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
