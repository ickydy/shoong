package controller.secret;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.PostDao;
import model.vo.Post;

@WebServlet("/private/post/write")
public class PostWriteController extends HttpServlet {

	@Override // 게시글을 조회
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PostDao postDao = new PostDao();

		try {
			List<Post> post = postDao.findAll();

			request.setAttribute("post", post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/private/post.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String userId = request.getParameter("user_id");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(sdf.parse(request.getParameter("write_at")).getTime());

			Post post = new Post();
			post.setId(id);
			post.setUserId(userId);
			post.setTitle(title);
			post.setContents(contents);
			post.setWriteAt(date);
			PostDao postDao = new PostDao();

			postDao.save(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/private/post");
	}
}

// 1안 완성
