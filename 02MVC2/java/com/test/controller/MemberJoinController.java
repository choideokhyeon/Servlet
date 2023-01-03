package com.test.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberJoinController implements SubController {

	private static String msg;
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		//System.out.println("[MemberJoinController]Start");
		
		try {
			//0 Get 구별
			String method = req.getMethod();
			if(method.equals("GET"))
			{
				System.out.println("[MemberJoinController] 요청 Method : " + method);
				req.getRequestDispatcher("/WEB-INF/view/member/join.jsp").forward(req, resp);
				return ;
			}
		
		
		//1 파라미터
		Map<String,String[]> params = req.getParameterMap();
		for(String name : params.keySet())
		{
			System.out.println("name : " + name + "|val : " + params.get(name)[0]);
		}
		
		
		//2 Validation
		boolean isvalid=isValid(params);
		if(!isvalid)
		{
			//유효성 체크 오류 발생시 전달할 메세지 + 이동될 경로
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("/WEB-INF/view/member/join.jsp").forward(req, resp);
			return ;
		}
		
		
		//3 Service
		
		
		//4 View
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		try
		{
			req.getRequestDispatcher("/WEB-INF/view/member/join.jsp").forward(req, resp);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private boolean isValid(Map<String, String[]> params)
	{
		msg = "제대로 입력하세요";
		return false;
	}

}
