package controller.secret;

import java.io.IOException;
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

		PostDao postDao = new PostDao();

		String sort = request.getParameter("sort");
		String search = request.getParameter("search");

		List<Post> posts = null;

		try {
			if (search != null && !search.equals("")) {
				posts = postDao.findByTitle(search);
				posts = postDao.findByTitleWithContent(search);
				//posts = postDao.findById(search);
				// 질문
			} else {
				String e = "검색값을 입력해주세요.";
				request.setAttribute("e", e);
			}

			request.setAttribute("posts", posts);
			request.getRequestDispatcher("/WEB-INF/private/community.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
	}
}