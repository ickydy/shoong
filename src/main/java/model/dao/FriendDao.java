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

	public boolean addFriend(String userId, String friendId) throws ClassNotFoundException { // 친구추가
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "INSERT INTO friends VALUES(friend_seq, ?, ?, 0, null, 0)";

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

	public List<Friend> findById(String userId) throws ClassNotFoundException { // 친구 검색.

		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {

			String sql = "select * from friends f join users u on f.friend_id = u.id where f.user_id =? and f.confirmed = 1 and spam = 0";

			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userId);

			ResultSet rs = pst.executeQuery();
			List<Friend> list = new ArrayList<Friend>();

			while (rs.next()) {
				Friend one = new Friend();

				one.setId(rs.getInt("id"));
				one.setUserId(rs.getString("user_id"));
				one.setFriendId(rs.getString("friend_id"));
				one.setConfirmed(rs.getInt("confirmed"));
				one.setConfirmAt(rs.getDate("confirm_at"));
				one.setSpam(rs.getInt("spam"));

				list.add(one);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Friend> findByFriendBirthDate(String userId, Date begin, Date end) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {
			String sql = "select * from friends f join users u on f.friend_id =u.id";
			sql += " where f.user_id =? and to_char(u.birth, 'mm-dd') between to_char(?,'mm-dd') and to_char(?, 'mm-dd')";
			sql += " and confirmed = 1 and spam = 0"; // 이러면

			PreparedStatement pst = conn.prepareStatement(sql);// 조건을 이렇게 많이 추가할 수도 있구나.

			pst.setString(1, userId);
			pst.setDate(2, begin);
			pst.setDate(3, end);

			ResultSet rs = pst.executeQuery();// 현재 여기 리저트셋에는 유저의 있는 컬럼들만 결과값으로 나오는데.
			List<Friend> list = new ArrayList<Friend>();
			while (rs.next()) {
				Friend one = new Friend();
				one.setId(rs.getInt("id"));
				one.setUserId(rs.getString("user_id"));
				one.setFriendId(rs.getString("friend_id"));
				one.setConfirmed(rs.getInt("confirmed"));
				one.setConfirmAt(rs.getDate("confirm_at"));
				one.setSpam(rs.getInt("spam"));

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

	public List<Friend> findSendRequest(String userId) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {
//			String sql = "select * from users u join friends f on u.id =f.user_id where u.id=? and f.confirmed=1"; 
			String sql = "select * from friends f join users u on f.frined_id = u.id where f.user_id = ? and f.confirmed = 0";

			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userId);

			ResultSet rs = pst.executeQuery();
			List<Friend> list = new ArrayList<>();
			while (rs.next()) {
				Friend one = new Friend();
				one.setId(rs.getInt("id"));
				one.setUserId(rs.getString("user_id"));
				one.setFriendId(rs.getString("friend_id"));
				one.setConfirmed(rs.getInt("confirmed"));
				one.setConfirmAt(rs.getDate("confirm_at"));
				one.setSpam(rs.getInt("spam"));

				User i = new User();
				i.setId(rs.getString("id"));
				i.setPassword(rs.getString("password"));
				i.setBirth(rs.getDate("birth"));
				i.setName(rs.getString("name"));
				i.setCountryId(rs.getString("country_id"));
				i.setGender(rs.getString("gender"));
				i.setOpenAccess(rs.getInt("open_access"));
				i.setAvatarId(rs.getInt("avatar_id"));

				one.setUser(i);
				list.add(one);

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

	public List<Friend> findReceiveRequest(String userId) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {
//			String sql = "select * from users u join friends f on u.id =f.friend_id where u.id=? and f.confirmed=0";
			String sql = "select * from friends f join users u on f.user_id =u.id where f.friend_id=? and f.confirmed=0";// 프렌드
			// 앞으로.
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userId);

			ResultSet rs = pst.executeQuery();
			List<Friend> list = new ArrayList<>();
			while (rs.next()) {
				Friend one = new Friend();

				one.setId(rs.getInt("id"));
				one.setUserId(rs.getString("user_id"));
				one.setFriendId(rs.getString("friend_id"));
				one.setConfirmed(rs.getInt("confirmed"));
				one.setConfirmAt(rs.getDate("confirm_at"));
				one.setSpam(rs.getInt("spam"));

				User i = new User();
				i.setId(rs.getString("id"));
				i.setPassword(rs.getString("password"));
				i.setBirth(rs.getDate("birth"));
				i.setName(rs.getString("name"));
				i.setCountryId(rs.getString("country_id"));
				i.setGender(rs.getString("gender"));
				i.setOpenAccess(rs.getInt("open_access"));
				i.setAvatarId(rs.getInt("avatar_id"));

				one.setUser(i);
				list.add(one); // 리시버 빠트린거 수정부분.

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

	public boolean updateSpam(Friend one) throws ClassNotFoundException { // 친구 차단상태로 두기.
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "UPDATE friends SET spam=1  WHERE friend_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, one.getFriendId());

			int n = pst.executeUpdate(); // 요청 전송하고 DB에서 응답을 받아옴.
			if (n == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Friend> findAllBlockedFriends() throws ClassNotFoundException { // 차단한 친구
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {
			String sql = "SELECT * FROM friends where spam= 1";

			PreparedStatement pst = conn.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			List<Friend> list = new ArrayList<>();
			while (rs.next()) {

				Friend one = new Friend();

				one.setId(rs.getInt("id"));
				one.setUserId(rs.getString("user_id"));
				one.setFriendId(rs.getString("friend_id"));
				one.setConfirmed(rs.getInt("confirmed"));
				one.setConfirmAt(rs.getDate("confirm_at"));
				one.setSpam(rs.getInt("spam"));

				User i = new User();
				i.setId(rs.getString("id"));
				i.setPassword(rs.getString("password"));
				i.setBirth(rs.getDate("birth"));
				i.setName(rs.getString("name"));
				i.setCountryId(rs.getString("country_id"));
				i.setGender(rs.getString("gender"));
				i.setOpenAccess(rs.getInt("open_access"));// 이게 뭐였
				i.setAvatarId(rs.getInt("avatar_id"));

				one.setUser(i);
				list.add(one); // 리시버 빠트린거 수정부분.

				/*
				 * int code = rs.getInt(1); String name = rs.getString(2); int price =
				 * rs.getInt(3); double minRate = rs.getDouble(4); double maxRate =
				 * rs.getDouble(5);
				 * 
				 * Item one = new Item(code, name, price, minRate, maxRate); list.add(one);
				 */
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
