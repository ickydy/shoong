package controller.main;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.vo.User;

@WebServlet("/index")
public class IndexController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");

		if (user == null) {
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
		} else {
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/private/index.jsp").forward(request, response);
		}
	}
}
