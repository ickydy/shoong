package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Avatar;
import model.vo.Country;
import model.vo.Friend;
import model.vo.User;

public class CountryDao {
	String url = "jdbc:oracle:thin:@13.125.229.23:1521:xe";
	String host = "shoong";
	String password = "1111";

	public boolean save(Country country) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			boolean result = false;
			String sql = "INSERT INTO COUNTRYS VALUES(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, country.getId());
			pstmt.setString(2, country.getName());
		

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
	
	public List<Country> findAll() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "SELECT * FROM COUNTRYS";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			List<Country> list = new ArrayList<>();
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");

				Country one = new Country(id, name);
				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Country findByCountryId(String id) throws ClassNotFoundException { // 친구 검색.

		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {

			String sql = "select * from countrys where id=?"; // 차단
			// 가져와야하니까.
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				Country one = new Country();
				one.setId(rs.getString("id"));
				one.setName(rs.getString("name"));
				return one;
			}
			return null;
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
				
				
				




}