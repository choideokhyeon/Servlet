package com.test.controller;

import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dto.MemberDTO;
import com.test.service.MemberService;

public class MemberJoinController implements SubController {

	private static String msg;
	
	private MemberService service = MemberService.getInstance();
	
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
		
		
		//1 파라미터 받기
		Map<String,String[]> params = req.getParameterMap();
		for(String name : params.keySet())
		{
			
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
		MemberDTO DTO = new MemberDTO();
		DTO.setEmail(params.get("email")[0]);
		DTO.setPwd(params.get("pwd")[0]);
		DTO.setPhone(params.get("phone")[0]);
		DTO.setZipcode(params.get("zipcode")[0]);
		DTO.setAddr1(params.get("addr1")[0]);
		DTO.setAddr2(params.get("addr2")[0]);
		boolean result = service.MemberJoin(DTO);
		
		
		//4 View(로그인 페이지로 이동)
		if(result)
		{
			//로그인
			msg = URLEncoder.encode("회원가입에 성공하셨습니다");
			
			req.setAttribute("msg", msg);
			String path = req.getContextPath();
			resp.sendRedirect(path + "/auth/login.do?msg="+msg);
			return ;
		}
		else
		{
			//회원가입 페이지로 이동
			msg = URLEncoder.encode("회원가입에 실패하셨습니다 다시 입력해주세요");
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("/WEB-INF/view/member/join.jsp").forward(req, resp);
			return ;
		}
		
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private boolean isValid(Map<String, String[]> params)
	{
		for(String name : params.keySet())
		{
			//공백 확인
			if(params.get(name)[0].isEmpty())
			{
				msg="<i class='bi bi-exclamation-triangle' style='color:orange;'></i> <span>공백은 포함할 수 없습니다</span>";
				return false;
			}
			
			//패스워드 복잡성 체크
			if(name.equals("pwd"))
			{
				String pwvalue = params.get(name)[0];
				  Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
				  Matcher passMatcher1 = passPattern1.matcher(pwvalue);
				  
				  if(!passMatcher1.find())
				  {
					  msg="<i class='bi bi-exclamation-triangle' style='color:orange;'></i> "
					  		+ "<span>패스워드는 영소문자/대문자/특수문자/숫자를 포함하는 8자 이상이여야 합니다</span>";
					  return false;
				  }
			}
		}
		
		//msg="<i class='bi bi-exclamation-triangle' style='color:orange;'></i> <span>유효성 검사 오류</span>";
		return true;
	}

}
