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

@WebServlet("/private/friends")
public class FriendsController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FriendDao findAll = new FriendDao();
		List<Friend> friend;
		try {
			friend = findAll.friendFindAll();
			request.setAttribute("friend", friend);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String userId = request.getParameter("userId");
		List<Friend> findByUserId;
		try {
			findByUserId = findAll.findById(userId);
			request.setAttribute("findByUserId", findByUserId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/private/friends.jsp").forward(request, response);

	}
}
