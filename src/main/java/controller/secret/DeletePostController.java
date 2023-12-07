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

@WebServlet("/private/post/delete")
public class DeletePostController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PostDao postDao = new PostDao();
		try {
//			List<Post> post = postDao.deleteById(id);
//			request.setAttribute("post", post);
			String uri = "";
			// 요청 들어온곳으로 sendRedirect
			response.sendRedirect(request.getContextPath() + uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
