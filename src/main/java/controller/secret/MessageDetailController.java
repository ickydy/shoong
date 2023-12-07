package controller.secret;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.MessageDao;
import model.vo.Message;

@WebServlet("/private/msg/detail")
public class MessageDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id")) ;// 일단 파리미터를 받을건 두고.

		MessageDao msgDao = new MessageDao();// 사용할 dao
		try {
			Message message = msgDao.findByMessageId(id);
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("/WEB-INF/private/msg/detail.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
