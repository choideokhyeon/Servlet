package com.test.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.test.dao.BoardDAO;
import com.test.dto.BoardDTO;
import com.test.dto.Criteria;
import com.test.dto.PageDTO;



public class BoardService {
	
	BoardDAO DAO = BoardDAO.getInstance();
	
	//싱글톤 패턴
	private static BoardService instance;
	public static BoardService getInstance()
	{
		if(instance == null)
			instance = new BoardService();
		return instance;
	}
	
	//디폴트 생성자
	private BoardService() {};
	
	//게시물 가져오기
//	public boolean GetBoardList(HttpServletRequest request) {
//		List<BoardDTO> list = DAO.SelectAll();
//		if(list != null)
//		{
//			request.setAttribute("list", list);
//			return true;
//		}
//		return false;
//	}
	
	//모든 게시물 가져오기
	public boolean GetBoardList(Criteria criteria, HttpServletRequest request) {
		
		//전체게시물 건수 받기
		int totalcount = DAO.getAmount();
		
		//PageDTO 만들기
		PageDTO PageDTO = new PageDTO(totalcount,criteria);
		
		//시작게시물 번호 구하기(수정)
		int startno = criteria.getPageno()*criteria.getAmount()-criteria.getAmount();
		
		//PageDTO를 Session에 저장
		HttpSession session = request.getSession(false);
		
		List<BoardDTO> list = DAO.SelectAll(startno,criteria.getAmount());
		if(list != null)
		{
			session.setAttribute("list", list);
			session.setAttribute("pagedto", PageDTO);
			return true;
		}
		return false;
	}
	
	public boolean PostBoard(BoardDTO DTO, HttpServletRequest request)
	{
		boolean flag = false;

		int result = DAO.Insert(DTO);
		if(result > 0)
			flag = true;
		
		return flag;
	}
	
}
