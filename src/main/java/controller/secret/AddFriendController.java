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

@WebServlet("/private/friends/add")
public class AddFriendController extends HttpServlet { // 여기 doget 에서 dopost로 변경.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");// 세션을 뽑아두고 여기서 get userId를 할거야.

		String userId = user.getId();
		String friendId = request.getParameter("friendId");

		FriendDao friendDao = new FriendDao();

		try {
			List<Friend> friends = friendDao.findByUserIdAndFriendId(userId, friendId);
			if (friends.size() == 0) {
				boolean addFriendResult = friendDao.addFriend(userId, friendId);
				response.sendRedirect(request.getContextPath() + "/private/friends");
			} else {
				response.sendRedirect(request.getContextPath() + "/private/friends?error=1");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		

	}

}
