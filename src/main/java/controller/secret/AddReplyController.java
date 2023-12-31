package controller.secret;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ReplyDao;
import model.vo.Reply;
import model.vo.User;

@WebServlet("/private/reply/add")
public class AddReplyController extends HttpServlet {

	@Override // 댓글 저장
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");

		String userId = user.getId();
		String contents = request.getParameter("contents");
		int postId = Integer.parseInt(request.getParameter("postId"));
		Date date = new Date(System.currentTimeMillis()); // 확인

		try {

			Reply reply = new Reply();
			reply.setId(0);
			reply.setUserId(userId);
			reply.setContents(contents);
			reply.setWriteAt(date);
			reply.setPostId(postId);

			ReplyDao replyDao = new ReplyDao();
			replyDao.save(reply);

		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/private/post?id=" + postId);
	}
}
