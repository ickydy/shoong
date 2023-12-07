package controller.secret;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FriendDao;
import model.vo.Friend;
import model.vo.User;

@WebServlet("/private/friends")
public class FriendsController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User)request.getSession().getAttribute("logonUser");

		String userId = user.getId(); // 세션에서 유저 아이디 뽑은 다음 진행.
		try {
			FriendDao friendDao = new FriendDao();
			List<Friend> friends = friendDao.findById(userId);
			request.setAttribute("friends", friends);

			List<Friend> sendRequests = friendDao.findSendRequest(userId);
			request.setAttribute("sendRequests", sendRequests);

			List<Friend> receiveRequests = friendDao.findReceiveRequest(userId);
			request.setAttribute("receiveRequests", receiveRequests);

			request.getRequestDispatcher("/WEB-INF/private/friends.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * FriendDao friendDao = new FriendDao();
		 * 
		 * try { List<Friend> friend = friendDao.friendFindAll();
		 * request.setAttribute("friend", friend); } catch (ClassNotFoundException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * String userId = request.getParameter("userId"); List<Friend> findByUserId;
		 * try { findByUserId = friendDao.findById(userId);
		 * request.setAttribute("findByUserId", findByUserId); } catch
		 * (ClassNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

	}
}
