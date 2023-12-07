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

@WebServlet("/private/msg/send")
public class SendMessageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("logonUser");
		String userId = user.getId();

		MessageDao messageDao = new MessageDao(); 
		try {
			List<Message> sendMessageResult = messageDao.findSendMessage(userId);
			request.setAttribute("sendMessageResult", sendMessageResult);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * String userId =request.getParameter("sendView");
		 * 
		 * MessageDao sendView = new MessageDao(); try { List<Message> list =
		 * sendView.findByUserId(userId); request.setAttribute("list", list); } catch
		 * (ClassNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		request.getRequestDispatcher("/WEB-INF/private/msg/send.jsp").forward(request, response);
	}
}
