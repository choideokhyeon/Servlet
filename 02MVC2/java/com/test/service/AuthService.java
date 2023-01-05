package com.test.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.bcrypt.BCrypt;

import com.test.dao.MemberDAO;
import com.test.dto.AuthDTO;
import com.test.dto.MemberDTO;

public class AuthService {
	
	BCrypt bc = new BCrypt();
	MemberDAO DAO = MemberDAO.getInstance();

	// 싱글톤 패턴
	private static AuthService instance;
	public static AuthService getInstance() {
		if (instance == null)
			instance = new AuthService();
		return instance;
	}
	private AuthService() {};
	
	
	
	//Login 확인
	public boolean LoginCheck(Map<String,String[]> params, HttpServletRequest request) {
		boolean flag = false;
		
		String email = params.get("email")[0];
		String pwd = params.get("pwd")[0];
		
		MemberDTO MDTO = DAO.Select(email);
		if(MDTO != null) //ID 일치여부 확인(DB)
		{
			if(bc.checkpw(pwd, MDTO.getPwd())) //PW 일치여부 확인(DB)
			{
				//둘 다 일치한다면 email과 grade(권한) 을 Session에 저장
				AuthDTO adto = new AuthDTO();
				adto.setEmail(email);
				adto.setGrade(MDTO.getGrade());
				
				//Session 유지시간 설정
				HttpSession session = request.getSession();
				session.setAttribute("authdto", "adto");
				session.setMaxInactiveInterval(300);
				
				//true 전달
				flag = true;
			}
		}
		
		return flag;
	}
	
	
	//Logout 처리
}
