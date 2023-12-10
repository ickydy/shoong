package filter;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.KeepTicketDao;
import model.dao.LogInLogDao;
import model.dao.UserDao;
import model.vo.KeepTicket;
import model.vo.LogInLog;
import model.vo.User;

public class TicketIdCookieFilter extends HttpFilter {
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		User user = (User) request.getSession().getAttribute("logonUser");

		if (user == null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("ticketId")) {
						String Id = cookie.getValue();
						KeepTicketDao ticketDao = new KeepTicketDao();
						try {
							KeepTicket keepTicket = ticketDao.findById(Id);

							Date now = new Date(System.currentTimeMillis());

							if (keepTicket != null && keepTicket.getExpiredAt().after(now)) {
								UserDao userDao = new UserDao();
								User found = userDao.findUserWithAvatarById(keepTicket.getUserId());
								request.getSession().setAttribute("logonUser", found);

								LogInLogDao logDao = new LogInLogDao();
								LogInLog log = new LogInLog(0, found.getId(), now, request.getRemoteAddr());
								logDao.save(log);
							}
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}

			chain.doFilter(request, response);
		} else {
			chain.doFilter(request, response);
		}

	}
}
