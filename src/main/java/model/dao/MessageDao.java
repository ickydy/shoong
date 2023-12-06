package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Message;

public class MessageDao {

	public boolean save(Message one) throws ClassNotFoundException {
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:xe", "shoong", "oracle")) {
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "INSERT INTO users VALUES(message_seq.nextval, ?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, one.getUserId());
			pst.setString(2, one.getFriendId());
			pst.setString(3, one.getContents());
			pst.setDate(4, one.getWriteAt());
			pst.setInt(5, one.getViewStatus());

			int n = pst.executeUpdate(); // 요청 전송하고 DB에서 응답을 받아옴.
			if (n == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Message> findByUserId(String userId) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:xe", "shoong", "oracle")) {
			String sql = "SELECT * FROM messages WHERE user_id=?";

			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userId);

			ResultSet rs = pst.executeQuery();
			List<Message> msg = new ArrayList<Message>();

			if (rs.next()) {
				Message one = new Message();
				one.setUserId(rs.getString("user_id"));
				one.setFriendId(rs.getString("friend_id"));
				one.setContents(rs.getString("contents"));
				one.setWriteAt(rs.getDate("write_at"));
				one.setViewStatus(rs.getInt("view_status"));

				msg.add(one);

			}
			return msg;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Message> findByFriendId(String friendId) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:xe", "shoong", "oracle")) {
			String sql = "SELECT * FROM messages WHERE friend_id=?";

			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, friendId);

			ResultSet rs = pst.executeQuery();
			List<Message> msg = new ArrayList<Message>();

			if (rs.next()) {
				Message one = new Message();
				one.setUserId(rs.getString("user_id"));
				one.setFriendId(rs.getString("friend_id"));
				one.setContents(rs.getString("contents"));
				one.setWriteAt(rs.getDate("write_at"));
				one.setViewStatus(rs.getInt("view_status"));

				msg.add(one);

			}
			return msg;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean deleteById(int id) throws ClassNotFoundException { // 특정 게시물만 지워야하니까 시크 id를 이용한다.
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "DELETE FROM messages WHERE id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);

			int n = pst.executeUpdate(); // 요청 전송하고 DB에서 응답을 받아옴. 삭제는 이게 맞을 듯.
			if (n == 1) { // PK로 삭제시엔 1 or 0
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public boolean readOrNot(Message r) throws ClassNotFoundException { // 객체를 넣어주는데.
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "UPDATE messages SET view_status=? where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, r.getViewStatus());
			pst.setInt(2, r.getId());

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
