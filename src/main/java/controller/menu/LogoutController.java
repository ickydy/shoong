package controller.menu;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet
public class LogoutController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();

		Cookie cookie = new Cookie("ticketId", null);
		cookie.setPath(request.getServletContext().getContextPath());
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		response.sendRedirect(request.getContextPath() + "/index");
	}
}
