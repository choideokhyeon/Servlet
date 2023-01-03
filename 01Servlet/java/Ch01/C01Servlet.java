package Ch01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletTest01") //경로 http://serverIP:port/01Servlet/ServletTest01
public class C01Servlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		//클라이언트 URL요청시 최초1번만 실행
		System.out.println("INIT 함수 실행");
	}
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		//클라이언트 URL요청시 마다 실행
		System.out.println("SERIVCE 함수 실행1");

		HttpServletResponse response = (HttpServletResponse)arg1;
		PrintWriter out = response.getWriter();
		out.print("TEST HELLO WORLD");
	}

	@Override
	public void destroy() {
		//서비스 종료 , 재시작 , 코드 수정시
		System.out.println("DESTROY 함수 실행");
	}

	
}
