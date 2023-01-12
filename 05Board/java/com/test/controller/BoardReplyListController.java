package com.test.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dto.ReplyDTO;
import com.test.service.BoardService;

public class BoardReplyListController implements SubController {

	private BoardService service = BoardService.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		try {
			// 1 파라미터
			Map<String, String[]> params = req.getParameterMap();

			// 2 유효성체크

			// 3 서비스
			ReplyDTO DTO = new ReplyDTO();
			DTO.setBno(params.get("bno")[0]);
			List<ReplyDTO> list = service.ReplyList(DTO);
			
			PrintWriter out = resp.getWriter();
			for(ReplyDTO RDTO : list)
			{
				out.println("<div>");
				out.println("<div style=\"margin: 10px;\">");
				out.println("<i class=\"bi bi-person-circle\" style=\"font-size: 2rem;\"></i>");	
				out.println("</div>");

				out.println("<div style=\"margin: 10px;\">");
				out.println("<div style=\"font-size: 0.8rem;\">");
				out.println("<span>" + RDTO.getEmail() + "</span> <span>" + RDTO.getRegdate() + "</span>");
				out.println("</div>");
				out.println("<div>" + RDTO.getContent() + "</div>"); 
				
				out.println("<div style=\"display: flex; justify-contents: left;\">");
				out.println("<i class=\"bi bi-hand-thumbs-up\"></i>&nbsp;<span>0</span>&nbsp;");
				out.println("<i class=\"bi bi-hand-thumbs-down\"></i>&nbsp;<span>0</span>&nbsp;");
						
				out.println("</div>");
				out.println("</div>");
				out.println("</div>");
				out.println("<hr>");
			}
			
			
			
		} catch (Exception e) {e.printStackTrace();}
	}
}
