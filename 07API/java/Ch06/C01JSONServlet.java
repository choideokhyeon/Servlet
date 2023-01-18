package Ch06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/meme.do")
public class C01JSONServlet extends HttpServlet {

	//0 doGet 함수 받아오기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//1 response 헤더 설정 -> json
		resp.setContentType("text/json; charset=UTF-8");
		
		
		//2 API 주소 저장
		String addr = "https://www.daegufood.go.kr/kor/api/tasty.html?mode=json&addr=" + URLEncoder.encode("달서구");
		
		
		//3 URL 연결
		URL url = new URL(addr);
		
		
		//4 API서버의 내용을 현 위치(JAVA)로 가져오기
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
		StringBuffer buff = new StringBuffer();
		String tmp = null;
		while(true)
		{
			tmp = br.readLine();
			if(tmp == null)
				break;
			buff.append(tmp);
		}
		
		
		//5 response에 json데이터 실어보내기
		PrintWriter out = resp.getWriter();
		out.print(buff.toString());
	}
	
	
}
