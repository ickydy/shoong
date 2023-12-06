package dummy;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FriendDao;
import model.vo.Friend;

public class ShoongDummyController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		FriendDao dummyDao = new FriendDao();

		for (int cnt = 1; cnt <= 30; cnt++) {

			Friend log = new Friend();

			log.setUserId("luffy"); // 마스터라는 이름의 자료들 대량생산.
			log.setFriendId("batman");
			log.setConfirmed(0);
			log.setConfirmAt(null);
			log.setSpam(0);

			try {
				dummyDao.dummyFriend(log);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
