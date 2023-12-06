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
import model.dao.UserDao;
import model.vo.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
        String loginPassword = request.getParameter("loginPassword");
        String keep = request.getParameter("keep"); // 로그인 유지

        try {
            UserDao userDao = new UserDao();
            User foundUser = userDao.findById(loginId);
            if (foundUser != null && foundUser.getPassword().equals(loginPassword)) {
                request.getSession().setAttribute("logonUser", foundUser);

                if ("on".equals(keep)) {
                    String id = UUID.randomUUID().toString();
                    Date expiredAt = new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30);
                    KeepTicket ticket = new KeepTicket(id, loginId, expiredAt);

                    KeepTicketDao keepTicketDao = new KeepTicketDao();
                    keepTicketDao.save(ticket);

                    Cookie cookie = new Cookie("ticketCode", id);
                    cookie.setPath(request.getServletContext().getContextPath());
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(cookie);
                }

                response.sendRedirect(request.getServletContext().getContextPath() + "/index");
            } else {
                String errorMessage = "잘못된 아이디 또는 비밀번호입니다.";
                request.setAttribute("error", errorMessage);
                request.getRequestDispatcher("/WEB-INF/view/user/login_form.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "로그인 처리 중 오류가 발생했습니다.";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("/WEB-INF/view/user/login_form.jsp").forward(request, response);
        }
    }
}