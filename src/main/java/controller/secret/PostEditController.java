package controller.secret;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.PostDao;
import model.vo.Post;
import model.vo.User;

@WebServlet("/private/post/edit")
public class PostEditController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");
		String postId = request.getParameter("id");

		try {
			if (postId != null && postId.matches("\\d+")) {
				PostDao postDao = new PostDao();
				Post post = postDao.findById(Integer.parseInt(postId));

				if (post.getUserId().equals(user.getId())) {
					request.setAttribute("post", post);
				} else {
					String e = "수정 권한이 없습니다.";
					request.setAttribute("e", e);
				}

			} else {
				String e = "존재하지 않는 게시글입니다.";
				request.setAttribute("e", e);
			}
			
			request.getRequestDispatcher("/WEB-INF/private/post/edit.jsp").forward(request, response);

		} catch (NumberFormatException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String postId = request.getParameter("postId");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		try {
			if (postId != null && postId.matches("\\d+")) {
				PostDao postDao = new PostDao();
				Post post = postDao.findById(Integer.parseInt(postId));

				post.setTitle(title);
				post.setContents(contents);
				
				postDao.update(post);
				
			} else {
				String e = "존재하지 않는 게시글입니다.";
				request.setAttribute("e", e);
			}
			
			response.sendRedirect(request.getContextPath() + "/private/post?id=" + postId);

		} catch (NumberFormatException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
