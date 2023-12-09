package controller.secret;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.MessageDao;
import model.vo.Message;
import model.vo.User;

@WebServlet("/private/msg/detail")
public class MessageDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");
		int id = Integer.parseInt(request.getParameter("id"));

		MessageDao msgDao = new MessageDao();
		try {
			Message message = msgDao.findByMessageId(id);

			if (!message.getUserId().equals(user.getId()) && !message.getFriendId().equals(user.getId())) {
				String e = "열람권한이 없습니다.";
				request.setAttribute("e", e);
				request.getRequestDispatcher("/WEB-INF/private/msg/detail.jsp").forward(request, response);
			}

			if (message.getViewStatus() == 0) {
				msgDao.read(id);
			}

			request.setAttribute("message", message);
			request.getRequestDispatcher("/WEB-INF/private/msg/detail.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
