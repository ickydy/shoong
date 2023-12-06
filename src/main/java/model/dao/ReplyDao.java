package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
			String sql = "INSERT INTO REPLYS VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getId());
			pstmt.setString(2, reply.getUserId());
			pstmt.setString(3, reply.getContents());
			pstmt.setDate(4, reply.getWriteAt());
			pstmt.setInt(5, reply.getPostId());

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
