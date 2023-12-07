package controller.secret;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ReplyDao;
import model.vo.Reply;

@WebServlet("/private/reply/delete")
public class DeleteReplyController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ReplyDao replydao = new ReplyDao();
		try {
			List<Reply> reply = replydao.deleteById();
			request.setAttribute("post", reply);
			String uri = "";
			// 요청 들어온곳으로 sendRedirect
			response.sendRedirect(request.getContextPath() + uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
