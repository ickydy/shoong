package controller.secret;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.MessageDao;
import model.vo.Message;
import model.vo.User;

@WebServlet("/private/msg/receive")
public class ReceiveMessageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("logonUser");

		String userId = user.getId();

		MessageDao messageDao = new MessageDao();
		
		try {
			List<Message> receiveMessages = messageDao.findReceiveMessage(userId);
			request.setAttribute("receiveMessages", receiveMessages);
			request.getRequestDispatcher("/WEB-INF/private/msg/receive.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		/*
		 * String friendId = request.getParameter("receiveView");
		 * 
		 * MessageDao receiveView = new MessageDao(); try { List<Message> list =
		 * receiveView.findByFriendId(friendId); request.setAttribute("list", list); }
		 * catch (ClassNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

	}

}
