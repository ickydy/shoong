package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.LogInLog;

public class LogInLogDao {
	String url = "jdbc:oracle:thin:@13.125.229.23:1521:xe";
	String host = "shoong";
	String password = "1111";

	public boolean save(LogInLog log) throws ClassNotFoundException {
		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "INSERT INTO LOGINLOGS VALUES(LOGINLOGS_SEQ.NEXTVAL, ?, ? ,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, log.getId());
			pstmt.setString(2, log.getUserId());
			pstmt.setDate(3, log.getLogAt());
			pstmt.setString(4, log.getLogFrom());

			int n = pstmt.executeUpdate();
			if (n == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<LogInLog> findByLogAt(Date date) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "SELECT * FROM LOGIN_LOGS WHERE TO_CHAR(LOG_AT,'YYYYMMDD') = TO_CHAR(?,'YYYYMMDD') ORDER BY LOG_AT ASC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, date);
			ResultSet rs = pstmt.executeQuery();
			List<LogInLog> logs = new ArrayList<LogInLog>();
			while (rs.next()) {
				LogInLog log = new LogInLog();
				log.setId(rs.getInt("id"));
				log.setUserId(rs.getString("user_id"));
				log.setLogAt(rs.getDate("log_at"));
				log.setLogFrom(rs.getString("log_from"));
				logs.add(log);
			}
			return logs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<LogInLog> findByUserId(String id) throws ClassNotFoundException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host, password)) {
			String sql = "SELECT * FROM LOGIN_LOGS WHERE USER_ID = ? ORDER BY LOG_AT DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();
			List<LogInLog> logs = new ArrayList<LogInLog>();
			while (rs.next()) {
				LogInLog log = new LogInLog();
				log.setId(rs.getInt("id"));
				log.setUserId(rs.getString("user_id"));
				log.setLogAt(rs.getDate("log_at"));
				log.setLogFrom(rs.getString("log_from"));
				logs.add(log);
			}
			return logs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteByUserId(String userId) throws ClassNotFoundException {
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection(url, host,
				password)) {
			String sql = "DELETE FROM LOGIN_LOGS WHERE USER_ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			int n = pstmt.executeUpdate(); //
			if (n >= 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
