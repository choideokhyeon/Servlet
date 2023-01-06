package com.test.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.test.dao.BoardDAO;
import com.test.dto.BoardDTO;
import com.test.dto.Criteria;



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
	
	public boolean GetBoardList(Criteria criteria, HttpServletRequest request) {
		
		//시작게시물 번호 구하기
					//페이지번호 * 게시글수 - 게시글수;
		int startno = criteria.getPageno()*criteria.getAmount()-criteria.getAmount();
		
		List<BoardDTO> list = DAO.SelectAll(startno,criteria.getAmount());
		if(list != null)
		{
			request.setAttribute("list", list);
			return true;
		}
		return false;
	}
}
