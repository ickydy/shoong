package controller.secret;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FriendDao;
import model.vo.User;

@WebServlet("/private/friends/confirm")
public class ConfirmController extends HttpServlet {
	
	@Override

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("logonUser");
		Date now = new Date(System.currentTimeMillis());

		String userId = user.getId();
		String friendId = request.getParameter("friendId");
		try {
			FriendDao friendDao = new FriendDao();
			boolean confirmResult = friendDao.confirm(now, friendId, userId);
			request.setAttribute("confirmResult", confirmResult);
			
			boolean cfduplicator = friendDao.confirmDuplicator(userId, friendId, 1, now);
			request.setAttribute("cfduplicator", cfduplicator);
			
			response.sendRedirect(request.getServletContext().getContextPath() + "/private/friends");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
