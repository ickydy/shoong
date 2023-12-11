package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Avatar;
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
			String sql = "INSERT INTO POSTS VALUES(POSTS_SEQ.NEXTVAL, ?, ?, ?, ?, 0)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, post.getUserId());
			pstmt.setString(2, post.getTitle());
			pstmt.setString(3, post.getContents());
			pstmt.setDate(4, post.getWriteAt());

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


	// 이 부분 확인
	public List<Post> findAll(String sort) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "SELECT * FROM POSTS";
			if (sort.equals("viewCount")) {
				sql += " order by view_count DESC";
			} else {
				sql += " order by write_at DESC";
			}
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			List<Post> list = new ArrayList<>();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				String userId = rs.getString("user_id");
				String title = rs.getString("title");
				String content = rs.getString("contents");
				Date writeAt = rs.getDate("write_at");
				int viewCnt = Integer.parseInt(rs.getString("view_count"));

				Post one = new Post(id, userId, title, content, writeAt, viewCnt);
				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 글 수정
	public boolean update(Post post) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		boolean result = false;
		try {
			Connection conn = DriverManager.getConnection(url, host, password);
			String sql = "UPDATE POSTS SET TITLE=?, CONTENTS=? WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post.getTitle());
			pstmt.setString(2, post.getContents());
			pstmt.setInt(3, post.getId());

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
	public boolean deleteById(String id) throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			boolean result = false;
			String sql = "DELETE FROM POSTS WHERE ID=?";
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

	// 게시글 ID로 검색
	public Post findById(int postId) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "SELECT * FROM POSTS WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				String title = rs.getString("title");
				String content = rs.getString("contents");
				Date date = rs.getDate("write_at");
				int viewCount = rs.getInt("view_count");

				Post one = new Post(id, userId, title, content, date, viewCount);

				return one;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 조회수 업데이트
	public boolean viewCountUpdate(Post post) throws ClassNotFoundException {
		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "UPDATE POSTS SET VIEW_COUNT=? WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, post.getViewCount());
			pstmt.setInt(2, post.getId());

			int n = pstmt.executeUpdate();
			if (n == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
