package controller.secret;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FriendDao;
import model.dao.MessageDao;

@WebServlet("/private/msg/delete")
public class DeleteMessageController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) //일단 두포스트로 해놓은 부분
			throws ServletException, IOException {
		
		int id =Integer.parseInt(request.getParameter("id"));
		
		MessageDao messageDao =new MessageDao();
		boolean delId;
		try {
			delId = messageDao.deleteById(id);
			request.setAttribute("delId", delId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		
		String uri = (String) request.getAttribute("requestUri");
		response.sendRedirect(request.getContextPath() + uri);
	}
}
