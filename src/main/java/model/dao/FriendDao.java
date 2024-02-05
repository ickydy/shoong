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
				"1111")) {
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "INSERT INTO friends VALUES(friends_seq.nextval, ?, ?, 0, null, 0)";

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
	
	public List<Friend> findByUserIdAndFriendId(String userId, String friendId) throws ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"1111")) {

			String sql = "select * from friends where (user_id = ? and friend_id = ?) or (user_id = ? and friend_id = ?)";
			// 가져와야하니까.
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userId);
			pst.setString(2, friendId);
			pst.setString(3, friendId);
			pst.setString(4, userId);

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

	public List<Friend> findById(String userId) throws ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"1111")) {

			String sql = "select * from friends f join users u on f.friend_id = u.id where f.user_id =? and f.confirmed = 1 and spam = 0";
			// 가져와야하니까.
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
				"1111")) {
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
				"1111")) {
//			String sql = "select * from users u join friends f on u.id =f.user_id where u.id=? and f.confirmed=1"; 
			String sql = "select * from friends f join users u on f.friend_id = u.id where f.user_id = ? and f.confirmed = 0";

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
				"1111")) {
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

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean updateSpam(int spam, String userId, String friendId) throws ClassNotFoundException { // 친구 차단.
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"1111")) {
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "UPDATE friends SET spam=?  WHERE user_id =? and friend_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, spam);
			pst.setString(2, userId);
			pst.setString(3, friendId);

			int n = pst.executeUpdate();
			if (n == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Friend> findAllBlockedFriends(String userId) throws ClassNotFoundException { // 차단한 친구리스트 뿌려두기
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"1111")) {
			String sql = "SELECT * from friends where user_id=? and spam= 1";

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

				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Friend> friendFindAll() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"1111")) {
			String sql = "SELECT * FROM friends where user_id ORDER BY friend_id DESC"; // where user_id 추가했음
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

				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}// friendFindAll의 끝.

	public boolean confirm(Date confirmedDate, String userId, String friendId) throws ClassNotFoundException { // 친구 차단.
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"1111")) {
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "UPDATE friends SET confirmed = 1, confirm_at =? WHERE user_id =? and friend_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setDate(1, confirmedDate);
			pst.setString(2, userId);
			pst.setString(3, friendId);

			int n = pst.executeUpdate(); // 요청 전송하고 DB에서 응답을 받아옴.
			if (n == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean confirmDuplicator(String userId, String friendId, int confirmed, Date confirmAt)
			throws ClassNotFoundException { // 친구추가
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"1111")) {
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "INSERT INTO friends VALUES(friends_seq.nextval, ?, ?, ?, ?, 0)";

			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userId);
			pst.setString(2, friendId);
			pst.setInt(3, confirmed);
			pst.setDate(4, confirmAt);

			int n = pst.executeUpdate(); // 요청 전송하고 DB에서 응답을 받아옴.
			if (n == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
