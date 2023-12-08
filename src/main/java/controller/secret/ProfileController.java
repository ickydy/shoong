package controller.secret;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CountryDao;
import model.vo.Country;
import model.vo.User;

@WebServlet("/private/profile")
public class ProfileController extends HttpServlet {

	@Override // 정보 열람 페이지
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");
		CountryDao countryDao = new CountryDao();

		try {
			Country country = countryDao.findByCountryId(user.getCountryId());

			if (user != null) {
				request.setAttribute("user", user);
				request.setAttribute("country", country);
				request.getRequestDispatcher("/WEB-INF/private/profile.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}