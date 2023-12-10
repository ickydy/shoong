package controller.menu;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.KeepTicketDao;
import model.dao.LogInLogDao;
import model.dao.UserDao;
import model.vo.KeepTicket;
import model.vo.LogInLog;
import model.vo.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String keep = request.getParameter("keep");

		LogInLogDao loginLogDao = new LogInLogDao(); 
		try {
			UserDao userDao = new UserDao();
			User foundUser = userDao.findUserWithAvatarById(id);

			if (foundUser != null && foundUser.getPassword().equals(password)) {
				request.getSession().setAttribute("logonUser", foundUser);
				LogInLog loginLog = new LogInLog(0, foundUser.getId(), new Date(System.currentTimeMillis()), request.getRemoteAddr());
				loginLogDao.save(loginLog);

				if (keep != null) {
					String code = UUID.randomUUID().toString();
					String userId = id;
					Date expiredAt = new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30);
					KeepTicket ticket = new KeepTicket(code, userId, expiredAt);

					KeepTicketDao keepTicketDao = new KeepTicketDao();
					keepTicketDao.save(ticket);
					Cookie cookie = new Cookie("ticketId", code);

					cookie.setPath(request.getServletContext().getContextPath());
					cookie.setMaxAge(60 * 60 * 24 * 30);
					response.addCookie(cookie);
				}

				response.sendRedirect(request.getServletContext().getContextPath() + "/index");
			} else {
				String e = "잘못된 아이디 또는 비밀번호입니다.";
				request.setAttribute("e", e);
				request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}