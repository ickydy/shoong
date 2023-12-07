package controller.menu;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.LogInLogDao;
import model.dao.UserDao;
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
			User foundUser = userDao.findById(id);

			if (foundUser != null && foundUser.getPassword().equals(password)) {
				request.getSession().setAttribute("logonUser", foundUser);
				saveLogInLog(id, "success", loginLogDao);

			} else {
				saveLogInLog(id, "fail", loginLogDao);
				String e = "잘못된 아이디 또는 비밀번호입니다.";
				request.setAttribute("e", e);
				request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	private void saveLogInLog(String userId, String status, LogInLogDao logInLogDao) {
		try {
			LogInLog log = new LogInLog();
			log.setUserId(userId);
			log.setLogAt(new Date(System.currentTimeMillis()));
			log.setLogFrom(status);
			logInLogDao.save(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}