package controller.secret;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FriendDao;
import model.dao.UserDao;
import model.vo.Friend;
import model.vo.User;

public class UserPageController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("logonUser");
		UserDao userDao = new UserDao();
		String userId = request.getParameter("userId");
		FriendDao friendDao = new FriendDao();
		try {
			List<Friend> viewUser = friendDao.findById(userId);
			for (Friend friend : viewUser) {
				User tempUser = userDao.findById(friend.getUserId());
				if (tempUser.getOpenAccess() == 1 && friend.getSpam() == 0) {
					request.setAttribute("viewUser", viewUser);
					request.getRequestDispatcher("/WEB-INF/private/userprofile.jsp").forward(request, response);
					// 경로 마음대로 정했음 미안해
				} else if (tempUser.getOpenAccess() == 0 && friend.getSpam() == 0) {
					if (friend.getConfirmed() == 1 && friend.getSpam() == 0) {
						request.setAttribute("viewUser", viewUser);
						request.getRequestDispatcher("/WEB-INF/private/userprofile.jsp").forward(request, response);
					} else {
						String e = "정보를 열람할 수 없습니다.";
						request.setAttribute("e", e);

					}
				}

			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
/*
 * boolean result = false;
 * 
 * String userId = request.getParameter("userId"); User user = (User)
 * request.getSession().getAttribute("logonUser"); User foundUser =
 * userDao.findByIdwithAvatar( userId ); List<Friend> friends =
 * friendDao.findById( user.getId() );
 * 
 * for(Friend one : friends) { // -> 친구관계이면 어차피 true 니까 if (
 * one.friendId.equals(userId) ) { result = true; break; } }
 * 
 * if ( foundUser.openAccess == 1 ) { result = true; }
 * 
 * request.setAttribute("result", result); request.setAttribute("foundUser",
 * foundUser);
 */
