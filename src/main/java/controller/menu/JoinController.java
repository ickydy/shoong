package controller.menu;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.AvatarDao;
import model.dao.CountryDao;
import model.dao.UserDao;
import model.vo.Avatar;
import model.vo.Country;
import model.vo.User;

@WebServlet("/join")
public class JoinController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 아바타 리스트
		AvatarDao avatarDao = new AvatarDao();
		try {
			List<Avatar> avatars = avatarDao.findAll();
			request.setAttribute("avatars", avatars);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 국가 리스트
		CountryDao countryDao = new CountryDao();
		try {
			List<Country> countries = countryDao.findAll();
			request.setAttribute("countries", countries);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String birth = request.getParameter("birth");
		String name = request.getParameter("name");
		String countryId = request.getParameter("countryId");
		String gender = request.getParameter("gender");
		String openAccess = request.getParameter("open_access");
		String avatarsId = request.getParameter("avatars_id");

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (id == null || id.equals("") || password == null || password.equals("") || birth == null
					|| birth.equals("") || name == null || name.equals("") || countryId == null || countryId.equals("")
					|| gender == null || gender.equals("") || openAccess == null || openAccess.equals("")
					|| avatarsId == null || avatarsId.equals("")) {
				request.setAttribute("error", "모든 것을 입력해주십시오.");
				request.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(request, response);
				return;
			}
			int openAccessInt = Integer.parseInt(openAccess);
			int avatarsIdInt = Integer.parseInt(avatarsId);

			Date birthDate = new Date(sdf.parse(request.getParameter("birth")).getTime());
			User user = new User(id, password, birthDate, name, countryId, gender, openAccessInt, avatarsIdInt);

			UserDao userDao = new UserDao();
			User found = userDao.findById(id);
			if (found == null) {
				boolean result = userDao.save(user);
				request.setAttribute("result", result);
				request.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(request, response);
			} else {
				String e = "이미 존재 하는 계정입니다.";
				request.setAttribute("e", e);
				request.setAttribute("tempUser", user);

				AvatarDao avatarDao = new AvatarDao();
				List<Avatar> avatars = avatarDao.findAll();
				CountryDao countryDao = new CountryDao();
				List<Country> countries = countryDao.findAll();
				request.setAttribute("avatars", avatars);
				request.setAttribute("countries", countries);
				request.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
