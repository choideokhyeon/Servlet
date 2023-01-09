package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.test.dto.BoardDTO;
import com.test.dto.Criteria;
import com.test.dto.MemberDTO;

public class BoardDAO {
	
	//DataSource
	private DataSource ds;
	
	
	//싱글톤 패턴
	private static BoardDAO instance;
	public static BoardDAO getInstance()
	{
		if(instance == null)
			instance = new BoardDAO();
		return instance;
	}
	
	
	private BoardDAO()
	{
		//DB관련 코드 적용(DBCP / HikariCP / Mabatis / JPA ..)
		//https://opentutorials.org/module/3569/21223
		try {
			//1. JNDI 서버 객체 생성
			InitialContext ic= new InitialContext();
			
			//2. DataSource 자원 찾기
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/mysql");
			
			System.out.println("[B.DAO] DS : " + ds);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//SELECT ALL
	public List<BoardDTO> SelectAll(int startno, int amount)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardDTO> list = new ArrayList();
		BoardDTO DTO = null;
		
		try {
			conn = ds.getConnection();
															//order by no desc no번호 역순 정렬 , limit ?페이지에,?개씩 표시
			pstmt = conn.prepareStatement("select * from tbl_board order by no desc limit ?,?");
			pstmt.setInt(1, startno);
			pstmt.setInt(2, amount);
			
			rs=pstmt.executeQuery();
			if(rs != null)
			{
				while(rs.next())
				{
					DTO = new BoardDTO();
					DTO.setNo(rs.getInt(1) + "");
					DTO.setEmail(rs.getString(2));
					DTO.setSubject(rs.getString(3));
					DTO.setContent(rs.getString(4));
					DTO.setRegdate(rs.getString(5));
					DTO.setCount(rs.getInt(6) + "");
					DTO.setDirpath(rs.getString(7));
					DTO.setFilename(rs.getString(8));
					DTO.setFilesize(rs.getString(9));
					list.add(DTO);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
			return null;
		} finally{
			try {rs.close();}catch(Exception e) {}
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return list;
	}


	
	public int getAmount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from tbl_board");
			rs=pstmt.executeQuery();
			
			if(rs != null)
			{
				rs.next();
				cnt = rs.getInt(1);				
			}
		} catch(Exception e){e.printStackTrace();}
		finally{
			try {rs.close();}catch(Exception e) {}
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return cnt;
	}
	
	
	
	public int Insert(BoardDTO DTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("insert into tbl_board values(?,?,?,?,now(),0,?,?,?)"); 
			pstmt.setString(1, null);
			pstmt.setString(2, DTO.getEmail());
			pstmt.setString(3, DTO.getSubject());
			pstmt.setString(4, DTO.getContent());
			pstmt.setString(5, DTO.getDirpath());
			pstmt.setString(6, DTO.getFilename());
			pstmt.setString(7, DTO.getFilesize());
			result = pstmt.executeUpdate();

		} catch(Exception e){e.printStackTrace();}
		finally{
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return result;
	}
}