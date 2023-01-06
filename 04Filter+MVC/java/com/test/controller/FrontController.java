package com.test.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

	private Map<String,SubController> list;
	
	//init
	@Override
	public void init(ServletConfig config) throws ServletException {
		String path = config.getServletContext().getContextPath(); // /02MVC2 경로 저장
		System.out.println("[FrontController] CONTEXT Path : " + path);
		list = new HashMap();
		
		//URL , SubController 저장
		//Member
		list.put(path + "/member/join.do", new MemberJoinController());
		
		//Board
		list.put(path + "/board/list.do", new BoardListController());
		list.put(path + "/board/post.do", new BoardPostController());
		
		//Notice
		list.put(path + "/notice/list.do", new NoticeListController());
		list.put(path + "/notice/post.do", new NoticePostController());
		
		//Auth
		list.put(path + "/auth/login.do", new LoginController());
		list.put(path + "/auth/logout.do", new LogoutController());
		
		//Main
		list.put(path + "/main.do", new MainController());
	}
	
	//service
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//문자셋 설정(Filter 이동예정)
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		//SubController 실행
		String uri = req.getRequestURI();
		System.out.println("[FrontController] URI : " + uri);
		SubController sub = list.get(uri);
		sub.execute(req, resp);
	}
}
