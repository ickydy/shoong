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

//		String userId = (String) request.getSession().getAttribute("logonUser"); 이부분이 조금 이상하다. 맞나?
		User user = (User) request.getSession().getAttribute("logonUser");
		String userId = user.getId();
		String friendId = request.getParameter("friend_id");// 넘어오는 파라미터 값으로는 유저아이디와 프렌드 아이디 두개뿐

		Friend one = new Friend(userId, friendId);// 일단 매개변수 두개로 객체를 생성.( 생성자에 새로 넣어놓음.)

		FriendDao spamFriendDao = new FriendDao();
		try {
			boolean blockFriend = spamFriendDao.updateSpam(userId, friendId); // 매개변수 객체에서 매개변수 스트링 두개로 변경.
			request.setAttribute("blockFriend", blockFriend);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/private/fiends");
	}

}
