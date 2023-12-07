package controller.secret;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FriendDao;
import model.dao.MessageDao;
import model.vo.Friend;
import model.vo.Message;
import model.vo.User;

@WebServlet("/private/msg")
public class MessageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");
		String userId = user.getId();

		FriendDao friendDao = new FriendDao();
		List<Friend> friendList;
		try {
			friendList = friendDao.findById(userId);
			request.setAttribute("friendList", friendList);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/private/msg.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 짰던 쿼리문 기준으로 넣어줘야할 것들을 ?를 기준으로 둔다.

		User user = (User) request.getSession().getAttribute("logonUser");// 세션 뽑기.
		Date now = new Date(System.currentTimeMillis());

		String userId = user.getId();
		String friendId = request.getParameter("friendId");
		String contents = request.getParameter("contents");
		int viewStatus = 0; // 일단 파라미터를 다 가지고 온다.

		Message msg = new Message(userId, friendId, contents, now, viewStatus);// 물음표 대로 넣어주는 것.

		MessageDao msgDao = new MessageDao();

		try {
			boolean result = msgDao.save(msg);

			request.setAttribute("result", result);// setAttribute

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/msg/send");// 경로설정 이렇게도 가능하구나.

	}
}
