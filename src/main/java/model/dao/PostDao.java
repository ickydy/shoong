package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Post;

public class PostDao {

	String url = "jdbc:oracle:thin:@13.125.229.23:1521:xe";
	String host = "shoong";
	String password = "1111";

	// 저장
	public boolean save(Post post) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			boolean result = false;
			String sql = "INSERT INTO POSTS VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post.getId());
			pstmt.setString(2, post.getUserId());
			pstmt.setString(3, post.getTitle());
			pstmt.setString(4, post.getContents());
			pstmt.setDate(5, post.getWriteAt());
			pstmt.setInt(6, post.getViewCount());

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

	// 글 수정
	public boolean update(Post post) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		boolean result = false;
		try {
			Connection conn = DriverManager.getConnection(url, host, password);
			String sql = "UPDATE POSTS SET C=?, NAME=?, OPEN_ACCESS=?, AVATAR_ID=? WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post.getId());
			pstmt.setString(2, post.getUserId());
			pstmt.setString(3, post.getTitle());
			pstmt.setString(4, post.getContents());
			pstmt.setDate(5, post.getWriteAt());
			pstmt.setInt(6, post.getViewCount());

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

	// 글 삭제
	public boolean deleteById(String userId) throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			boolean result = false;
			String sql = "DELETE FROM POSTS WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

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

	// 제목으로 글 검색
	public List<Post> findByTitle(String keyword) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "SELECT * FROM POSTS WHERE TITLE LIKE ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, '%' + keyword + '%');
			ResultSet rs = pstmt.executeQuery();
			List<Post> list = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				String title = rs.getString("title");
				String content = rs.getString("contents");
				Date date = rs.getDate("write_at");
				int viewCount = rs.getInt("view_count");

				Post one = new Post(id, userId, title, content, date, viewCount);

				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 제목 + 내용으로 글 검색
	public List<Post> findByTitleWithContent(String keyword) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "SELECT * FROM POSTS WHERE CONTENTS LIKE ? OR TITLE LIKE ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, '%' + keyword + '%');
			pstmt.setString(2, '%' + keyword + '%');

			ResultSet rs = pstmt.executeQuery();
			List<Post> list = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				String title = rs.getString("title");
				String content = rs.getString("contents");
				Date date = rs.getDate("write_at");
				int viewCount = rs.getInt("view_count");

				Post one = new Post(id, userId, title, content, date, viewCount);

				list.add(one);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 유저 ID로 글 검색
	public List<Post> findById(String name) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "SELECT * FROM POSTS WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			List<Post> list = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				String title = rs.getString("title");
				String content = rs.getString("contents");
				Date date = rs.getDate("write_at");
				int viewCount = rs.getInt("view_count");

				Post one = new Post(id, userId, title, content, date, viewCount);

				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}