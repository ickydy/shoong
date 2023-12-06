package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Avatar;

public class AvatarDao {
	String url = "jdbc:oracle:thin:@13.125.229.23:1521:xe";
	String host = "shoong";
	String password = "1111";

	public List<Avatar> findAll() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "SELECT * FROM AVATARS";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			List<Avatar> list = new ArrayList<>();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				String alt = rs.getString("alt");
				String imageUrl = rs.getString("img_url");

				Avatar one = new Avatar(id, alt, imageUrl);
				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Avatar findById(String avatarId) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "SELECT * FROM AVATARS WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, avatarId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				String alt = rs.getString("alt");
				String imageUrl = rs.getString("img_url");

				Avatar one = new Avatar(id, alt, imageUrl);
				return one;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}