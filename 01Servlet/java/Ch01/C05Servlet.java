package Ch01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class C05Servlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("DOGET!!");
		//Forwarding(request공유)
		req.getRequestDispatcher("/WEB-INF/05Login.jsp").forward(req, resp);
		
		return ;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("DOPOST!!");
		//1 파라미터 받기
		String email =  req.getParameter("email");
		
		//2 입력값 검증
		
		//3 Service
		HttpSession session = req.getSession();
		session.setAttribute("email", email);
		
		//4 view
		req.getRequestDispatcher("/WEB-INF/05Main.jsp").forward(req, resp);
		
		

	}

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("SERVICE!!");
		
		String method = req.getMethod();
		System.out.println("METHOD : " + method);
		if(method.equals("GET")) {
			doGet(req,res);
		}else if(method.equals("POST")) {
			doPost(req,res);
		}
		
	}
 
	//dohandle(별도정의)
	
}
