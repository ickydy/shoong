package controller.secret;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FriendDao;
import model.dao.MessageDao;
import model.vo.Friend;
import model.vo.Message;
import model.vo.User;

@WebServlet("/private/msg")
public class MessageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");
		String userId = user.getId();

		try {
			FriendDao friendDao = new FriendDao();
			List<Friend> friends = friendDao.findById(userId);
			request.setAttribute("friends", friends);

			request.getRequestDispatcher("/WEB-INF/private/msg.jsp").forward(request, response);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");
		Date now = new Date(System.currentTimeMillis());

		String userId = user.getId();
		String friendId = request.getParameter("friendId");
		String contents = request.getParameter("contents");
		int viewStatus = 0;

	
		
		Message msg = new Message(userId, friendId, contents, now, viewStatus);

		MessageDao msgDao = new MessageDao();
		FriendDao friendDao = new FriendDao();

		try {

			List<Friend> realFriend = friendDao.findById(userId); //dao를 두고 이걸 시작점으로 코딩을 시작한다.


			int con = 0;
			for (Friend one : realFriend) {//여기까지 one이 객체로 나옴. 여기서 뽑아 쓸 수 있음.
				if (one.getFriendId().equals(friendId)) {// 이부분이 프랜드 아이디 체크 후 컨펌드를 뽑아주는 것.
					con = one.getConfirmed();
				}
			}

			if (con == 1) {

				boolean result = msgDao.save(msg);
				request.setAttribute("result", result);// setAttribute
				response.sendRedirect(request.getContextPath() + "/private/msg/send");
			} else {
				response.sendRedirect(request.getContextPath() + "/private/msg/send");

			}

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
