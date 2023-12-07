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

@WebServlet("/private/friendss")
public class confirmController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("logonUser");
		Date now = new Date(System.currentTimeMillis());
		
		
		
		String userId = user.getId();
		String friendId = request.getParameter("friendId");
		try {
		FriendDao friendDao = new FriendDao();
		boolean confirmResult = friendDao.confirm(now, userId, friendId);
		request.setAttribute("confirmResult", confirmResult);//confirm
		
		boolean cfduplicator = friendDao.confirmDuplicator(userId, friendId, 1, now);
		request.setAttribute("cfduplicator", cfduplicator);//confirmDuplicator
		
		request.getRequestDispatcher("/WEB-INF/private/msg.jsp").forward(request, response);
		
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
		
	}

}
