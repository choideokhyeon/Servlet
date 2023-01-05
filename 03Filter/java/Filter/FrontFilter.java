package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
// import javax.servlet.annotation.WebFilter;

//@WebFilter("/main.do")
public class FrontFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// 전
		System.out.println("[FrontFilter] Start");
		chain.doFilter(req, resp);
		System.out.println("[FrontFilter] End");
		// 후
	}

}
