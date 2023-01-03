package Ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletTest02")
public class C02Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("DoGet함수 실행");
		
		//서블릿 리퀘스트에 문자형식 설정
		req.setCharacterEncoding("UTF-8");
		
		//서블릿 리스폰스에 파일 타입, 문자형식 설정
		resp.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		
		PrintWriter out = resp.getWriter();
		out.print("EMAIL : " + email + "<br>");
		out.print("PWD : " + pwd + "<br>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("DoPost함수 실행");
		
		req.setCharacterEncoding("UTF-8");
		
		resp.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		
		PrintWriter out = resp.getWriter();
		out.print("EMAIL : " + email + "<br>");
		out.print("PWD : " + pwd + "<br>");
	}
	
}
