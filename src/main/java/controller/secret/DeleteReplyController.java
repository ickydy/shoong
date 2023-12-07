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
			String id = request.getParameter("id");
			boolean delete = replydao.deleteById(id);
			
			if(delete) {
			String uri = request.getRequestURI();
			response.sendRedirect(request.getContextPath() + uri);
				
			}else {
				String e ="삭제에 실패했습니다.";
				request.setAttribute("e", e);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
			
	
	}
}

