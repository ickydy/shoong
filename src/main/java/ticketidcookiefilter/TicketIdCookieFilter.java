package ticketidcookiefilter;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.KeepTicketDao;
import model.dao.UserDao;
import model.vo.KeepTicket;
import model.vo.User;


@WebFilter({"/*"})
public class TicketIdCookieFilter extends HttpFilter {
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		User user = (User)request.getSession().getAttribute("logonUser");
		
		if(user == null) { 
		Cookie found = null;

		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie one : cookies) {
				if (one.getName().equals("ticketId")) {
					found = one;
					break;
				}
			}
		}
		

			if (found != null) {
				String id = found.getValue();
				KeepTicketDao keepTicketDao = new KeepTicketDao();

				try {
					KeepTicket foundTicket = keepTicketDao.findById(id);
					Date now = new Date(System.currentTimeMillis());

					if (foundTicket != null && foundTicket.getExpiredAt().before(now)) {// if문 시작.
						String userId = foundTicket.getUserId();
						UserDao userDao = new UserDao();
						User foundUser = userDao.findUserWithAvatarById(userId);// 이게 파운드온유저.
						request.getSession().setAttribute("logonuser", foundUser);

					} // if문 끝

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("티켓 쿠키 필터부분에서 에러가 났습니다.");
				}

			}

		}
		
			chain.doFilter(request, response);

	}
}
