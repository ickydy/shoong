package controller.secret;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDao;
import model.vo.User;

@WebServlet("/private/edit")
public class EditController extends HttpServlet {

	@Override // 프로필 수정 페이지로 forward
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/private/edit.jsp").forward(request, response);
	}

	@Override // 프로필 수정 후 정보 열람 페이지로 redirect
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		int openAccess = Integer.parseInt(request.getParameter("openAccess"));
		int avatarId = Integer.parseInt(request.getParameter("avatarId"));

		User user = new User();
		UserDao userDao = new UserDao();
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		user.setOpenAccess(openAccess);
		user.setAvatarId(avatarId);

		try {
			int result = userDao.update(user);
			if (result > 0) {
				
				response.sendRedirect(request.getContextPath() + "/private/profile");
			} else {
				
				response.sendRedirect("errorPage.jsp"); 
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			response.sendRedirect("errorPage.jsp"); 
		}
	}

}
