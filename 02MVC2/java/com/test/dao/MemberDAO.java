package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.test.dto.MemberDTO;

public class MemberDAO {
	//DataSource
	private DataSource ds;
	
	
	//싱글톤 패턴
	private static MemberDAO instance;
	public static MemberDAO getInstance()
	{
		if(instance == null)
			instance = new MemberDAO();
		return instance;
	}
	
	
	private MemberDAO()
	{
		//DB관련 코드 적용(DBCP / HikariCP / Mabatis / JPA ..)
		//https://opentutorials.org/module/3569/21223
		try {
			//1. JNDI 서버 객체 생성
			InitialContext ic= new InitialContext();
			
			//2. DataSource 자원 찾기
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/mysql");
			
			System.out.println("[M.DAO] DS : " + ds);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	//INSERT
	public int Insert(MemberDTO DTO)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("insert into tbl_member values(?,?,?,?,?,?,?)");
			pstmt.setString(1, DTO.getEmail());
			pstmt.setString(2, DTO.getPwd());
			pstmt.setString(3, DTO.getPhone());
			pstmt.setString(4, DTO.getZipcode());
			pstmt.setString(5, DTO.getAddr1());
			pstmt.setString(6, DTO.getAddr2());
			pstmt.setString(7, "0");	//Grade
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return result;
	}
	
}