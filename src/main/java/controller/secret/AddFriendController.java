package controller.secret;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FriendDao;
import model.vo.User;

@WebServlet("/private/friends/add")
public class AddFriendController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = (String) request.getSession().getAttribute("logonUser");
		String friendId = request.getParameter("friendId");

		FriendDao friendDao = new FriendDao();
		boolean addFriend;
		try {
			addFriend = friendDao.addFriend(userId, friendId);
			request.setAttribute("addFriend", addFriend);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/private/friends");

	}
}
