package controller.secret;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CountryDao;
import model.dao.FriendDao;
import model.dao.UserDao;
import model.vo.Country;
import model.vo.Friend;
import model.vo.User;

@WebServlet("/private/user/profile")
public class UserPageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean result = false;

		User logonUser = (User) request.getSession().getAttribute("logonUser");
		String userId = request.getParameter("id");

		try {
			UserDao userDao = new UserDao();
			FriendDao friendDao = new FriendDao();
			User user = userDao.findUserWithAvatarById(userId);
			CountryDao countryDao = new CountryDao();
			Country country = countryDao.findByCountryId(user.getCountryId());
			List<Friend> friends = friendDao.findById(logonUser.getId());

			boolean friend = false;
			for (Friend one : friends) { // -> 친구관계이면 어차피 true 니까
				if (one.getFriendId().equals(userId)) {
					result = true;
					friend = true;
					break;
				}
			}

			if (user.getOpenAccess() == 1) {
				result = true;
			}

			List<Friend> checkRequest = friendDao.findByUserIdAndFriendId(userId, logonUser.getId());
			if (checkRequest.size() > 0) {
				friend = true;
			}

			request.setAttribute("result", result);
			request.setAttribute("friend", friend);
			request.setAttribute("user", user);
			request.setAttribute("country", country);

			request.getRequestDispatcher("/WEB-INF/private/user/profile.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}