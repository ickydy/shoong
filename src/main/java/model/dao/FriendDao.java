package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Friend;
import model.vo.User;

public class FriendDao {

	public Friend findById(String id) throws ClassNotFoundException { // 친구 검색.

		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:xe", "shoong", "oracle")) {

			String sql = "SELECT * FROM friends WHERE user_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				Friend one = new Friend();

				one.setUserId(rs.getString("user_id"));
				one.setFriendId(rs.getString("friend_id"));
				one.setConfirmed(rs.getInt("confirmed"));
				one.setConfirmAt(rs.getDate("confirm_at"));
				return one;

			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean addFriend(String userId, String friendId) throws ClassNotFoundException { // 친구추가
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "INSERT INTO users VALUES(friends_seq, ?, ?, 0, null)";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, userId);
			pst.setString(2, friendId);

			int n = pst.executeUpdate(); // 요청 전송하고 DB에서 응답을 받아옴.
			if (n == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Friend> findByFriendBirthDate(String friendId, Date begin, Date end) throws ClassNotFoundException {// 친구
																													// 생일
																													// 알림.
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {
			String sql = "select u.name, u.birth from user u join friend f on u.id=f.user_id where f.friend_id=? and f.confirmed =1"; // 이러면
																																		// 프렌드vo에
																																		// user객체를
																																		// 추가해야한다.

			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, friendId);

			ResultSet rs = pst.executeQuery();// 현재 여기 리저트셋에는 유저의 있는 컬럼들만 결과값으로 나오는데.
			List<Friend> list = new ArrayList<Friend>();
			while (rs.next()) {
				Friend one = new Friend();
				User i = new User();
				i.setName(rs.getString("name"));
				i.setBirth(rs.getDate("birth"));
				one.setUser(i);

				list.add(one);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Friend> findSendRequest(String id) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:1521:xe", "shoong",
				"oracle")) {
			String sql = "select f.confirmed, f. from user u join friend f oin u.id =f.user_id where u.id=? and f.confirmed=1"; // 일단
																																// 모든
																																// 결과
																																// 가져온다.
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, id);

			ResultSet rs = pst.executeQuery();
			List<Friend> list = new ArrayList<>();
			while (rs.next()) {
				Friend log = new Friend();

				log.setUserId(rs.getString("user_id"));
				log.setFriendId(rs.getString("friend_id"));
				log.setConfirmed(rs.getInt("confirmed"));
				log.setConfirmAt(rs.getDate("confirm_at"));

				User i = new User();
				i.setId(rs.getString("id"));
				i.setPassword(rs.getString("password"));
				i.setBirth(rs.getDate("birth"));
				i.setName(rs.getString("name"));
				i.setCountryId(rs.getString("country_id"));
				i.setGender(rs.getString("gender"));
				i.setOpenAccess(rs.getInt("open_access"));
				i.setAvatarId(rs.getInt("avatar_id"));

				/*
				 * log.setNo(rs.getInt("no")); log.setUserId(rs.getString("user_id"));
				 * log.setAmt(rs.getInt("amt")); log.setSpendAt(rs.getDate("spend_at"));
				 * log.setUseDesc(rs.getString("use_desc"));
				 * log.setCategoryId(rs.getInt("category_id"));
				 * 
				 * Category c = new Category(); c.setId(rs.getInt("id"));
				 * c.setName(rs.getString("name")); log.setCategory(c);// 이게 제일 중요해. 세팅해서 여기에다
				 * 넣어주는구나.
				 * 
				 * list.add(log);
				 */

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Friend> findReceiveRequest(String id) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:1521:xe", "shoong",
				"oracle")) {
			String sql = "select * from user u join friend f oin u.id =f.user_id where u.id=? and f.confirmed=0";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, id);

			ResultSet rs = pst.executeQuery();
			List<Friend> list = new ArrayList<>();
			while (rs.next()) {
				Friend log = new Friend();

				log.setUserId(rs.getString("user_id"));
				log.setFriendId(rs.getString("friend_id"));
				log.setConfirmed(rs.getInt("confirmed"));
				log.setConfirmAt(rs.getDate("confirm_at"));

				User i = new User();
				i.setId(rs.getString("id"));
				i.setPassword(rs.getString("password"));
				i.setBirth(rs.getDate("birth"));
				i.setName(rs.getString("name"));
				i.setCountryId(rs.getString("country_id"));
				i.setGender(rs.getString("gender"));
				i.setOpenAccess(rs.getInt("open_access"));
				i.setAvatarId(rs.getInt("avatar_id"));

				/*
				 * log.setNo(rs.getInt("no")); log.setUserId(rs.getString("user_id"));
				 * log.setAmt(rs.getInt("amt")); log.setSpendAt(rs.getDate("spend_at"));
				 * log.setUseDesc(rs.getString("use_desc"));
				 * log.setCategoryId(rs.getInt("category_id"));
				 * 
				 * Category c = new Category(); c.setId(rs.getInt("id"));
				 * c.setName(rs.getString("name")); log.setCategory(c);// 이게 제일 중요해. 세팅해서 여기에다
				 * 넣어주는구나.
				 * 
				 * list.add(log);
				 */

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
