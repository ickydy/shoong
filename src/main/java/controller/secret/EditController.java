package controller.secret;

import java.io.IOException;
import java.sql.Date;

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

		// 아바타 리스트 뽑기
		request.getRequestDispatcher("/WEB-INF/private/edit.jsp").forward(request, response);
	}

	@Override // 프로필 수정 후 정보 열람 페이지로 redirect
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User found = (User) request.getSession().getAttribute("logonUser");

		String id = found.getId();
		String password = request.getParameter("password");
		Date birth = found.getBirth();
		String name = request.getParameter("name");
		String countryId = found.getCountryId();
		String gender = found.getGender();
		int openAccess = Integer.parseInt(request.getParameter("openAccess"));
		int avatarId = Integer.parseInt(request.getParameter("avatarId"));

		User user = new User(id, password, birth, name, countryId, gender, openAccess, avatarId);
		UserDao userDao = new UserDao();

		try {
			int result = userDao.update(user);
			if (result > 0) {

				response.sendRedirect(request.getContextPath() + "/private/profile");
			} else {
				String e = "정보를 업데이트 하는 중 오류가 발생하였습니다. 다시 시도해주세요.";
				request.setAttribute("e", e);
				
				// 아바타 리스트 뽑기
				request.getRequestDispatcher("/WEB-INF/private/edit.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
