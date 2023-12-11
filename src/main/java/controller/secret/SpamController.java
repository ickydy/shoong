package controller.secret;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FriendDao;
import model.vo.Friend;
import model.vo.User;

@WebServlet("/private/friends/spam")
public class SpamController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");
		String userId = user.getId();
		String friendId = request.getParameter("friendId");// 넘어오는 파라미터 값으로는 유저아이디와 프렌드 아이디 두개뿐

		FriendDao spamFriendDao = new FriendDao();
		try {
			boolean result = spamFriendDao.updateSpam(userId, friendId); // 매개변수 객체에서 매개변수 스트링 두개로 변경.
			request.setAttribute("result", result);
			response.sendRedirect(request.getContextPath() + "/private/friends");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
