package controller.secret;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDao;
import model.vo.User;

@WebServlet("/private/user/delete")
public class UserDeleteController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
		}

		UserDao userDao = new UserDao();
		try {
			userDao.deleteById(user.getId());
			request.getSession().invalidate(); 
			
			Cookie cookie = new Cookie("ticketId", null);
			cookie.setPath(request.getServletContext().getContextPath());
			cookie.setMaxAge(0);
			response.addCookie(cookie);

			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
