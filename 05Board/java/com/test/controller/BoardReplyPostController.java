package com.test.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dto.AuthDTO;
import com.test.dto.ReplyDTO;
import com.test.service.BoardService;

public class BoardReplyPostController implements SubController {

	private BoardService service = BoardService.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		try {
			// 1 파라미터
			Map<String, String[]> params = req.getParameterMap();

			// 2 유효성체크

			// 3 서비스
			// ReplyDTO에 데이터 저장
			HttpSession session = req.getSession(false);
			AuthDTO ADTO = (AuthDTO) session.getAttribute("authdto");

			ReplyDTO RDTO = new ReplyDTO();
			RDTO.setBno(params.get("bno")[0]);
			RDTO.setContent(params.get("comment")[0]);
			RDTO.setEmail(ADTO.getEmail());

			boolean flag = service.replyPost(RDTO);
			if (flag) {
				PrintWriter out = resp.getWriter();
				out.print("댓글 저장 성공");
			}
		} catch (Exception e) {e.printStackTrace();}
	}
}
