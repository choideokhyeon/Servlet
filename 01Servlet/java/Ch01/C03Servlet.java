package Ch01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/auth/login.do")
public class C03Servlet extends HttpServlet {

	//doGet
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("DoGet");
		
		//Forwarding(request공유) vs Redirect(request새로)
		req.getRequestDispatcher("/Ch01/03Login.jsp").forward(req, resp);
		return ;
	}

	//doPost
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("DoPost");
		
		//1 파라미터 받기
		String email = req.getParameter("email");
		
		//2 입력값 검증
		//3 Service
		HttpSession session = req.getSession();
		session.setAttribute("email", email);
		
		//4 View(Redirect로)
		resp.sendRedirect("/01Servlet/Ch01/03Main.jsp");
	}

	//Service
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Service");
		
		String method = req.getMethod();
		System.out.println("Method : " + method);
		if(method.equals("GET"))
		{
			doGet(req,res);
		}
		else if(method.equals("POST"))
		{
			doPost(req,res);
		}
	}
	
	//doHandle(별도 정의)
	
}
