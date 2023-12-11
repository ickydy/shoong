package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Reply;

public class ReplyDao {

	String url = "jdbc:oracle:thin:@13.125.229.23:1521:xe";
	String host = "shoong";
	String password = "1111";

	// 댓글 저장
	public boolean save(Reply reply) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			boolean result = false;
			String sql = "INSERT INTO REPLYS VALUES(replys_seq.nextval, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getUserId());
			pstmt.setString(2, reply.getContents());
			pstmt.setDate(3, reply.getWriteAt());
			pstmt.setInt(4, reply.getPostId());

			int n = pstmt.executeUpdate();
			if (n == 1) {
				result = true;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// 댓글 저장
	public List<Reply> findByPostId(int postId) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			boolean result = false;
			String sql = "select * from replys where post_id=? order by write_at asc";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, postId);
			
			ResultSet rs = pstmt.executeQuery();
			List<Reply> list = new ArrayList<>();
			while (rs.next()) {
				Reply reply = new Reply();
				reply.setId(rs.getInt("id"));
				reply.setUserId(rs.getString("user_id"));
				reply.setContents(rs.getString("contents"));
				reply.setWriteAt(rs.getDate("write_at"));
				reply.setPostId(rs.getInt("post_id"));
				
				list.add(reply);
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 댓글 수정
	public boolean update(Reply reply) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		boolean result = false;
		try {
			Connection conn = DriverManager.getConnection(url, host, password);
			String sql = "UPDATE REPLYS SET CONTENTS=? WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, reply.getContents());
			pstmt.setInt(2, reply.getId());

			int n = pstmt.executeUpdate();
			if (n == 1) {
				result = true;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}

	// 댓글 삭제
	public boolean deleteById(String id) throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			boolean result = false;
			String sql = "DELETE FROM REPLYS WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			int n = pstmt.executeUpdate();
			if (n == 1) {
				result = true;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
