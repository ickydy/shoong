package filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.vo.User;

public class LogonFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String uri = request.getRequestURI();
		request.getSession().setAttribute("requestUri", uri);
		
		User user = (User) request.getSession().getAttribute("logonUser");

		if (user != null) {
			chain.doFilter(request, response);

		} else {
			if (uri.endsWith("/community.jsp")) {// 이페이지들을 제외하고는 나머지는 다 리다이렉트
				chain.doFilter(request, response);
			}
			response.sendRedirect(request.getContextPath() + "/login");

		}
	}

}
