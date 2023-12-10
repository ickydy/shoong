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
				
		try {
			FriendDao friendDao = new FriendDao();
			List<Friend> friends = friendDao.findById(userId);
			request.setAttribute("friends", friends);
	
			request.getRequestDispatcher("/WEB-INF/private/msg.jsp").forward(request, response);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");
		Date now = new Date(System.currentTimeMillis());

		String userId = user.getId();
		String friendId = request.getParameter("friendId");
		String contents = request.getParameter("contents");
		int viewStatus = 0;

	
		
		Message msg = new Message(userId, friendId, contents, now, viewStatus);

		MessageDao msgDao = new MessageDao();

		try {
			boolean result = msgDao.save(msg);

			request.setAttribute("result", result);// setAttribute
			response.sendRedirect(request.getContextPath() + "/msg/send");// 경로설정 이렇게도 가능하구나.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
