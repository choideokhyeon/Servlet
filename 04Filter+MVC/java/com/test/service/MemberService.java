package com.test.service;

import org.mindrot.bcrypt.BCrypt;

import com.test.dao.MemberDAO;
import com.test.dto.MemberDTO;

public class MemberService {
	
	MemberDAO DAO = MemberDAO.getInstance();
	BCrypt bc = new BCrypt();
	
	
	//싱글톤 패턴
	private static MemberService instance;
	public static MemberService getInstance()
	{
		if(instance == null)
			instance = new MemberService();
		return instance;
	}
	private MemberService() {}
	
	
	//회원가입
	public boolean MemberJoin(MemberDTO DTO) {
		boolean flag = false;
		//pwd를 해시화 해서 저장 작업
		DTO.setPwd(bc.hashpw(DTO.getPwd() , bc.gensalt() ));
		
		int result = DAO.Insert(DTO);
		if(result > 0)
			flag = true;
		
		return flag;
	}
}
