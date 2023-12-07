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

		String id = request.getParameter("id");// 일단 파리미터를 받을건 두고.

		MessageDao mDao = new MessageDao();// 사용할 da
		try {
			Message detailContents = mDao.findByMessageId(id);
			
			request.setAttribute("detailContents", detailContents);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/private/msg/detail.jsp").forward(request, response);

	}
}
