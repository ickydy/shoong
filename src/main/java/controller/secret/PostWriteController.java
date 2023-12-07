package controller.secret;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.PostDao;
import model.vo.Post;
import model.vo.User;

@WebServlet("/private/post/write")
public class PostWriteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("logonUser");
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/private/post/write.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(sdf.parse(request.getParameter("writeAt")).getTime());

			Post post = new Post();

			post.setId(0); // 확인
			post.setUserId(userId);
			post.setTitle(title);
			post.setContents(contents);
			post.setWriteAt(date);
			post.setViewCount(0); // 확인
			PostDao postDao = new PostDao();

			postDao.save(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/private/post");
	}
}

// 1안 완성
