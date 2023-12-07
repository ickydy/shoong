package controller.secret;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.PostDao;
import model.vo.Post;

@WebServlet("/private/post")
public class PostController extends HttpServlet {

	@Override // 글 내용과 햇당 글의 댓글 표시 페이지로 forward
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * TORICHES String id = request.getParameter("id"); if (id == null ||
		 * id.equals("")) {
		 * 
		 * } int talkId = Integer.parseInt(id);
		 * 
		 * TalkProcessor talkProcessor = new TalkProcessor(); Talk talk =
		 * talkProcessor.findById(talkId); if (talk == null) {
		 * 
		 * } talk.setViewCnt(talk.getViewCnt()+1); talkProcessor.update(talk);
		 * 
		 * DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		 */
		String postIdStr = request.getParameter("id");
		if (postIdStr == null || postIdStr.equals("")) {
			String e = "id가 올바르지 않습니다.";
			request.setAttribute("error", e);
			return;
		}
		PostDao postDao = new PostDao();
		try {
			Post post = (Post) postDao.findById(postIdStr);
			if (post != null) {
				post.setViewCount(post.getViewCount() + 1);
				postDao.viewCountUpdate(post);
				request.setAttribute("post", post);
				request.getRequestDispatcher("/WEB-INF/private/post.jsp").forward(request, response);
			} else {
				String e = "게시글이 존재하지 않습니다.";
				request.setAttribute("error", e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
					}
		

}