package controller.secret;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.PostDao;
import model.dao.ReplyDao;

@WebServlet("/private/post/delete")
public class DeletePostController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PostDao Postdao = new PostDao();

		try {
			String id = request.getParameter("id");
			boolean delete = Postdao.deleteById(id);

			if (delete) {
				// 어디로 보낼지 모르겠음
			} else {
				String e = "삭제에 실패했습니다.";
				request.setAttribute("e", e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
