package controller.secret;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDao;
import model.vo.User;

@WebServlet("/private/user/search")
public class UserSearchController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyword = request.getParameter("keyword");
		UserDao userDao = new UserDao();

		try {
			if (keyword != null) {

				List<User> searchUsers = userDao.findByIdOrName(keyword);
				if (searchUsers != null) {
					request.setAttribute("searchUsers", searchUsers);
					List<User> recommendedUsers = userDao.findRecommendUsers();
					request.setAttribute("recommendedUsers", recommendedUsers);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		request.getRequestDispatcher("/WEB-INF/private/user/search.jsp").forward(request, response);
	}

}