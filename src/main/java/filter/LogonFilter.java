package filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/pirvate/*")// private안의 모든 폴더에 적용되는 로그온필터.
public class LogonFilter extends HttpFilter {
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String uri = request.getRequestURI();
		request.getSession().setAttribute("requestUri", uri);
		
		if(request.getSession().getAttribute("logonuser") != null) {
			chain.doFilter(request, response);
			
			
		} else {
			String path = request.getServletContext().getContextPath();
			
			if(uri.endsWith("/index.jsp") || uri.endsWith("post.jsp")) {// 이페이지들을 제외하고는 나머지는 다 리다이렉트
				chain.doFilter(request, response);
				
			}
			
			response.sendRedirect(path + "/login");
			
			
			
			
		}
	}
	
	
	
	

}
