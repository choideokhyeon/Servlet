package com.test.service;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.test.dao.BoardDAO;
import com.test.dto.AuthDTO;
import com.test.dto.BoardDTO;
import com.test.dto.Criteria;
import com.test.dto.PageDTO;
import com.test.dto.ReplyDTO;

public class BoardService {

	BoardDAO DAO = BoardDAO.getInstance();
	private String uploadDir;

	// 싱글톤 패턴
	private static BoardService instance;

	public static BoardService getInstance() {
		if (instance == null)
			instance = new BoardService();
		return instance;
	}

	// 디폴트 생성자
	private BoardService() {
	};

	// 게시물 가져오기
//	public boolean GetBoardList(HttpServletRequest request) {
//		List<BoardDTO> list = DAO.SelectAll();
//		if(list != null)
//		{
//			request.setAttribute("list", list);
//			return true;
//		}
//		return false;
//	}

	
	// 모든 게시물 가져오기(페이지 구현)
	public boolean GetBoardList(Criteria criteria, HttpServletRequest request, HttpServletResponse response) {
		
		//쿠키 생성(board/read.do 새로고침시 조회수 반복증가 방지)  
		Cookie init = new Cookie("reading", "true");
		response.addCookie(init);

		// 전체게시물 건수 받기
		int totalcount = DAO.getAmount();

		// PageDTO 만들기
		PageDTO PageDTO = new PageDTO(totalcount, criteria);

		// 시작게시물 번호 구하기(수정)
		int startno = criteria.getPageno() * criteria.getAmount() - criteria.getAmount();

		// PageDTO를 Session에 저장
		HttpSession session = request.getSession(false);

		List<BoardDTO> list = DAO.SelectAll(startno, criteria.getAmount());
		if (list != null) {
			session.setAttribute("list", list);
			session.setAttribute("pagedto", PageDTO);
			return true;
		}
		return false;
	}

	
	// 게시물 추가하기(파일x)
//	public boolean PostBoard(BoardDTO DTO, HttpServletRequest request)
//	{
//		boolean flag = false;
//
//		int result = DAO.Insert(DTO);
//		if(result > 0)
//			flag = true;
//		
//		return flag;
//	}

	// 게시물 추가하기(파일 업로드)
	public boolean PostBoard(BoardDTO DTO, HttpServletRequest request) {
		boolean flag = false;

		// 디렉토리 경로 설정
		System.out.println("DIRPATH : " + request.getServletContext().getRealPath("/"));
		uploadDir = request.getServletContext().getRealPath("/") + "upload";
		
		//접속한 이메일 계정 확인
		HttpSession session = request.getSession(false);
		AuthDTO ADTO = (AuthDTO)session.getAttribute("authdto");
		
		//UUID(랜덤값)으로 폴더 생성
		UUID uuid = UUID.randomUUID();
		String path = uploadDir + File.separator + ADTO.getEmail() + File.separator + uuid;
		
		//추출된 파일정보 저장용 Buffer
		StringBuffer filelist = new StringBuffer();
		StringBuffer filesize = new StringBuffer();
		
		
		try {

			Collection<Part> parts = request.getParts();
			for (Part part : parts) {
				if (part.getName().equals("files"))
				{
					System.out.println("-------------------------------------------");
//					System.out.println("PART명 : " + part.getName());
//					System.out.println("PART크기 : " + part.getSize());
//					for (String name : part.getHeaderNames()) {
//						System.out.println("Header Name : " + name);
//						System.out.println("Header Value : " + part.getHeader(name));
//					}
					
					//파일명 추출
					System.out.println("파일명 : " + getFileName(part));
					String filename = getFileName(part);
					
					if(!filename.equals(""))	//파일이 있다면
					{
						//폴더 생성
						File dir = new File(path);
						if(!dir.exists())
						{
							dir.mkdirs();
						}
						
						//업로드
						part.write(path + File.separator + filename);
						
						//파일명 DB Table에 추가 위한 저장
						filelist.append(filename + ";");
						
						//디렉토리 경로 DB Table에 추가 위한 저장
						filesize.append(part.getSize() + ";");
					}
					
					System.out.println("-------------------------------------------");
				}
			}
			DTO.setDirpath(uuid + "");
			DTO.setFilename(filelist.toString());
			DTO.setFilesize(filesize.toString());
			
			//DB연결
			int result = DAO.Insert(DTO);
			if (result > 0)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}


		return flag;
	}

	
	// 파일 이름 추출
	private String getFileName(Part part) {
//		----------------------------------------
//		<<예제>>
//		PART명 : files
//		PART크기 : 2953
//		Header Name : content-disposition
//		Header Value : form-data; name="files"; filename="sql expert.txt"
//		Header Name : content-type
//		Header Value : text/plain
//		----------------------------------------
		
		String contentDisp = part.getHeader("content-disposition");
		//String contentDisp = form-data; name="files"; filename="sql expert.txt"
		
		String[] tokens = contentDisp.split(";");
		//[form-data,name="files",filename="sql expert.txt"]
		
		String filename = tokens[tokens.length - 1];
		//tokens에서 제일 마지막 인덱스 불러오기(filename="sql expert.txt")
		
		return filename.substring(11,filename.length()-1);
		//filename="" 잘라내기
	}

	
	// 게시글 읽기
	public boolean GetBoard(int bno, HttpServletRequest req, HttpServletResponse resp) {
		boolean flag = false;
		
		Cookie[] cookies = req.getCookies();
		for(Cookie cookie : cookies)
		{
			if(cookie.getName().equals("reading"))
			{
				if(cookie.getValue().equals("true"))
				{
					//조회수 증가
					DAO.Update(bno);
					
					//쿠키 value 변경
					cookie.setValue("false");
					resp.addCookie(cookie);
				}
			}
		}
		
		BoardDTO DTO = DAO.Select(bno);
		if(DTO!=null)
		{
			//session에 추가한 정보
			//pagedto / authdto / + boarddto
			HttpSession session = req.getSession(false);
			session.setAttribute("boarddto", DTO);
			flag = true;
		}
		
		return flag;
	}
	
	
	//단일 파일 다운로드
	public void download(HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			
		//파라미터
		String filename = req.getParameter("filename");
		String uuid = req.getParameter("uuid");
		
		//이메일 정보 확인
		HttpSession session = req.getSession(false);
		AuthDTO ADTO = (AuthDTO)session.getAttribute("authdto");
		String email = ADTO.getEmail();
		
		//경로설정
		String path = req.getServletContext().getRealPath("/");
		path += "upload" + File.separator + email + File.separator + uuid;
		//		upload/email/63b6877c-ec83-4738-90b9-ba58da0b53f5
		System.out.println("BoardService : " + path);
		
		//헤더설정
		resp.setHeader("Content-Type", "application/octet-stream;charset=UTF-8");
		resp.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename,"UTF-8").replaceAll("\\+", "%20"));
		
		//스트림형성
		FileInputStream fin = new FileInputStream(path + File.separator + filename);
		ServletOutputStream bout = resp.getOutputStream();
		
		//다운로드 처리
		int read = 0;
		byte[] buff = new byte[4096];
		while(true)
		{
			read = fin.read(buff, 0 , buff.length);
			if(read == -1)
				break;
			bout.write(buff, 0, read);
		}
		bout.flush();
		bout.close();
		fin.close();
		
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	//zip 파일 다운로드
	public void downloadzip(HttpServletRequest req, HttpServletResponse resp) {
		try {
			
			//파라미터
//			String filename = req.getParameter("filename");
			String uuid = req.getParameter("uuid");
			
			//이메일 정보 확인
			HttpSession session = req.getSession(false);
			AuthDTO ADTO = (AuthDTO)session.getAttribute("authdto");
			String email = ADTO.getEmail();
			
			//경로설정
			String path = req.getServletContext().getRealPath("/");
			path += "upload" + File.separator + email + File.separator + uuid;
			//		upload/email/63b6877c-ec83-4738-90b9-ba58da0b53f5
			System.out.println("BoardService : " + path);
			
			
			// ------------------------------------
			FileInputStream fin = null;
			ZipEntry zipEntry = null;
			File dir = new File(path);
			File filelist[] = dir.listFiles();
			
			//헤더설정
			resp.setHeader("Content-Type", "application/octet-stream;charset=UTF-8");
			resp.setHeader("Content-Disposition", "attachment; filename=All.zip");
			
			//스트림형성
			ServletOutputStream bout = resp.getOutputStream();
			ZipOutputStream zout = new ZipOutputStream(bout);
			
			byte[] buff = new byte[4096];
			
			for(File file:filelist)
			{
				fin = new FileInputStream(file);
				ZipEntry zip = new ZipEntry(file.getName().toString());
				zout.putNextEntry(zip);
				while (true) 
				{
					int data = fin.read(buff,0,buff.length);
					if (data == -1)
						break;
					zout.write(buff,0,data);
				}
				
				zout.closeEntry();
				fin.close();
			}
			zout.close();
		}catch(Exception e) {e.printStackTrace();}
	}

	
	//댓글 쓰기
	public boolean replyPost(ReplyDTO DTO) {
		boolean flag = false;
		
		int result = DAO.Insert(DTO);
		if(result > 0)
			flag = true;
		
		return flag;
	}
	

	//댓글 불러오기
	public List<ReplyDTO> ReplyList(ReplyDTO DTO) {

		return DAO.SelectAll(DTO);
	}
	
	
	
	//댓글 수 조회
	public int getReplyCount(int bno) {
		return DAO.getReplyAmount(bno);
	}

	
	
}
