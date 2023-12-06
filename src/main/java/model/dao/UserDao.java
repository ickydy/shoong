package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Avatar;
import model.vo.User;

public class UserDao {
	String url = "jdbc:oracle:thin:@13.125.229.23:1521:xe";
	String host = "shoong";
	String password = "1111";

	public boolean save(User user) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			boolean result = false;
			String sql = "INSERT INTO USERS VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setDate(3, user.getBirth());
			pstmt.setString(4, user.getName());
			pstmt.setString(5, user.getCountryId());
			pstmt.setString(6, user.getGender());
			pstmt.setInt(7, user.getOpenAccess());
			pstmt.setInt(8, user.getAvatarId());

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

	public User findById(String userId) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "SELECT * FROM USERS WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setBirth(rs.getDate("birth"));
				user.setName(rs.getString("name"));
				user.setCountryId(rs.getString("country_id"));
				user.setGender(rs.getString("gender"));
				user.setOpenAccess(rs.getInt("open_access"));
				user.setAvatarId(rs.getInt("avatar_id"));
				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User findUserWithAvatarById(String userId) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "SELECT u.*, a.ALT, a.IMG_URL FROM USERS u LEFT JOIN AVATARS a ON u.AVATAR_ID = a.ID WHERE u.ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setBirth(rs.getDate("birth"));
				user.setName(rs.getString("name"));
				user.setCountryId(rs.getString("avatar_id"));
				user.setGender(rs.getString("gender"));
				user.setOpenAccess(rs.getInt("open_access"));
				user.setAvatarId(rs.getInt("avatar_id"));

				Avatar a = new Avatar();
				a.setId(rs.getInt("avatar_id"));
				a.setAlt(rs.getString("alt"));
				a.setImgUrl(rs.getString("img_url"));
				user.setAvatars(a);

				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteById(String userId) throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			boolean result = false;
			String sql = "DELETE FROM USERS WHERE ID=?";
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

	public int update(User user) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(url, host, password);
			String sql = "UPDATE USERS SET PASSWORD=?, NAME=?, OPEN_ACCESS=?, AVATAR_ID=? WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setInt(3, user.getOpenAccess());
			pstmt.setInt(4, user.getAvatarId());
			pstmt.setString(5, user.getId());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<User> findByIdOrName(String keyword) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "SELECT * FROM USERS WHERE ID LIKE ? OR NAME ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, '%' + keyword + '%');
			pstmt.setString(2, '%' + keyword + '%');
			ResultSet rs = pstmt.executeQuery();

			List<User> list = new ArrayList<>();
			while (rs.next()) {
				String id = rs.getString("id");
				String password = rs.getString("password");
				Date birth = rs.getDate("birth");
				String name = rs.getString("name");
				String countryId = rs.getString("country_id");
				String gender = rs.getString("gender");
				int openAccess = rs.getInt("open_access");
				int avatarId = rs.getInt("avatar_id");

				Avatar avatars = new Avatar();
				avatars.setAlt(rs.getString("alt"));
				avatars.setImgUrl(rs.getString("img_url"));

				User user = new User(id, password, birth, name, countryId, gender, openAccess, avatarId, avatars);
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}