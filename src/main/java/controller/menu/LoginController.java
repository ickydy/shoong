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
import model.dao.UserDao;
import model.vo.KeepTicket;
import model.vo.User;

import model.vo.Avatar;


@WebServlet("/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 String id = request.getParameter("id");
		 String password = request.getParameter("password");
		 String birth = request.getParameter("birth");
		 String name = request.getParameter("name");
		 String countryId = request.getParameter("country_id");
		 String gender = request.getParameter("gender");
		 int openAccess = Integer.parseInt(request.getParameter("open_access"));
		 int avatarId = Integer.parseInt(request.getParameter("avatar_id"));
		 
		 
		 
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
        String password = request.getParameter("password");
        String keep = request.getParameter("keep"); 

        try {
            UserDao userDao = new UserDao();
            User foundUser = userDao.findById(id);
            if (foundUser != null && foundUser.getPassword().equals(password)) {
                request.getSession().setAttribute("logonUser", foundUser);

                if (keep != null) {
                    String code = UUID.randomUUID().toString();
                    String userId = id;
                    Date expiredAt = new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30);
                    KeepTicket ticket = new KeepTicket(id, userId, expiredAt);

                    KeepTicketDao keepTicketDao = new KeepTicketDao();
                    keepTicketDao.save(ticket);

                    Cookie cookie = new Cookie("ticketId", id);
                    cookie.setPath(request.getServletContext().getContextPath());
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(cookie);
                }

                response.sendRedirect(request.getServletContext().getContextPath() + "/index");
            } else {
                String errorMessage = "잘못된 아이디 또는 비밀번호입니다.";
                request.setAttribute("error", errorMessage);
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
           
        }
    }
}