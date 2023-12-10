package controller.secret;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDao;
import model.vo.User;

@WebServlet("/private/user/delete")
public class UserDeleteController {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("lononUser");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
		}

		UserDao userDao = new UserDao();
		try {
			userDao.deleteById(user.getId());
			request.getSession().invalidate(); 
			//쿠키 삭제 getTicket id

			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
