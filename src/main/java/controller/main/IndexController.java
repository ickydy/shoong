package controller.main;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FriendDao;
import model.dao.PostDao;
import model.dao.UserDao;
import model.vo.Friend;
import model.vo.Post;
import model.vo.User;

@WebServlet("/index")
public class IndexController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("logonUser");

		if (user == null) {
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
		} else {

			String userId = user.getId();
			try {
				FriendDao friendDao = new FriendDao();
				List<Friend> friends = friendDao.findById(userId);

				UserDao userDao = new UserDao();
				for (Friend friend : friends) {
					User found = userDao.findUserWithAvatarById(friend.getFriendId());
					friend.setUser(found);
				}
				request.setAttribute("friends", friends);

				List<Friend> receiveRequests = friendDao.findReceiveRequest(userId);

				for (Friend friend : receiveRequests) {
					User found = userDao.findUserWithAvatarById(friend.getUserId());
					friend.setUser(found);
				}
				request.setAttribute("receiveRequests", receiveRequests);
				
				PostDao postDao = new PostDao();
				List<Post> posts = postDao.findAll("date");
				request.setAttribute("posts", posts);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/private/index.jsp").forward(request, response);
		}
	}
}
