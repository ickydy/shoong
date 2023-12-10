package controller.secret;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.PostDao;
import model.vo.Post;

@WebServlet("/private/community")
public class CommunityController extends HttpServlet {

	@Override // 정렬, 검색
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sort = request.getParameter("sort");
		String search = request.getParameter("search");
		String keyword = request.getParameter("keyword");

		PostDao postDao = new PostDao();

		try {

			if (sort == null && search == null && keyword == null) {
				List<Post> posts = postDao.findAll("date");
				request.setAttribute("posts", posts);
				request.getRequestDispatcher("/WEB-INF/private/community.jsp").forward(request, response);
			} else if (sort != null && search == null && keyword == null) {
				List<Post> posts = postDao.findAll(sort);
				request.setAttribute("posts", posts);
				request.getRequestDispatcher("/WEB-INF/private/community.jsp").forward(request, response);

			} else if (sort == null && search != null && keyword != null) {
				if (search.equals("title")) {
					List<Post> posts = postDao.findByTitle(keyword);
					request.setAttribute("posts", posts);
					request.getRequestDispatcher("/WEB-INF/private/community.jsp").forward(request, response);
				} else if (search.equals("titleWithContent")) {
					List<Post> posts = postDao.findByTitleWithContent(keyword);
					request.setAttribute("posts", posts);
					request.getRequestDispatcher("/WEB-INF/private/community.jsp").forward(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
