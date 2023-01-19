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

@WebServlet("/tour.do")
public class C02XMLServlet extends HttpServlet {

	//0 doGet 함수 받아오기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//1 response 헤더 설정 -> XML
		resp.setContentType("text/xml; charset=UTF-8");
		
		
		//2 API 주소 저장
		String pageNo = req.getParameter("pageNo");
		String numOfRows = req.getParameter("numOfRows");
		String servicekey = "N60a24fTPmXBXzkmgISMaLaR64QhlHpuqh7v%2Bc%2Fd065AhSzt46FUEBRkaRbFAJVbt5SkHsk4xUzm4VTvJJIvYw%3D%3D";
		String addr = "http://apis.data.go.kr/6270000/getTourKorAttract/getTourKorAttractList?pageNo="+pageNo+"&numOfRows="+numOfRows+"&ServiceKey="+servicekey;
		
		
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
		
		//5 response에 xml데이터 실어보내기
		PrintWriter out = resp.getWriter();
		out.print(buff.toString());
	}
	
	
}
