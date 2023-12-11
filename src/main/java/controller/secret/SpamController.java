package controller.secret;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FriendDao;
import model.vo.User;

@WebServlet("/private/friends/spam")
public class SpamController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");
		String userId = user.getId();
		String friendId = request.getParameter("friendId");

		FriendDao spamFriendDao = new FriendDao();
		try {
			boolean result = spamFriendDao.updateSpam(1, userId, friendId);
			request.setAttribute("result", result);
			response.sendRedirect(request.getContextPath() + "/private/friends");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
