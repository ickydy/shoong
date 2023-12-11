package controller.secret;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.PostDao;
import model.dao.ReplyDao;
import model.vo.Post;
import model.vo.Reply;

@WebServlet("/private/post")
public class PostController extends HttpServlet {

	@Override // 글 내용과 햇당 글의 댓글 표시 페이지로 forward
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String postIdStr = request.getParameter("id");
		if (postIdStr == null || postIdStr.equals("")) {
			String e = "id가 올바르지 않습니다.";
			request.setAttribute("e", e);
			return;
		}
		PostDao postDao = new PostDao();
		ReplyDao replyDao = new ReplyDao();
	
		try {
			Post post = (Post) postDao.findById(Integer.parseInt(postIdStr));
			if (post != null) {
				post.setViewCount(post.getViewCount() + 1);
				postDao.viewCountUpdate(post);
				request.setAttribute("post", post);
				
				List<Reply> replys = replyDao.findByPostId(post.getId());
				request.setAttribute("replys", replys);
				
				request.getRequestDispatcher("/WEB-INF/private/post.jsp").forward(request, response);
			} else {
				String e = "게시글이 존재하지 않습니다.";
				request.setAttribute("e", e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
					}
}