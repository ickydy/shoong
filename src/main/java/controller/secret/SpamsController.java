package controller.secret;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FriendDao;
import model.dao.UserDao;
import model.vo.Friend;
import model.vo.User;

@WebServlet("/private/friends/spams")
public class SpamsController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");
		FriendDao friendDao = new FriendDao();
		UserDao userDao = new UserDao();

		try {
			List<Friend> spams = friendDao.findAllBlockedFriends(user.getId());
			for (Friend friend : spams) {
				User found = userDao.findUserWithAvatarById(friend.getFriendId());
				friend.setUser(found);
			}
			request.setAttribute("spams", spams);
			request.getRequestDispatcher("/WEB-INF/private/friends/spam.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");
		FriendDao friendDao = new FriendDao();
		String friendId = (String) request.getParameter("friendId");
		try {
			friendDao.updateSpam(0, user.getId(), friendId);
			response.sendRedirect(request.getContextPath() + "/private/friends/spams");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
