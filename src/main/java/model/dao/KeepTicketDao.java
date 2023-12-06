package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.vo.KeepTicket;

public class KeepTicketDao {

	public boolean save(KeepTicket ticket) throws ClassNotFoundException {
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {
			boolean result = false;
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "INSERT INTO KEEP_TICKETS VALUES(?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, ticket.getId());
			pst.setString(2, ticket.getUserId());
			pst.setDate(3, ticket.getExpiredAt());

			int n = pst.executeUpdate(); // 요청 전송하고 DB에서 응답을 받아옴.
			if (n == 1) {
				result = true;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public KeepTicket findByCode(String ticketCode) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {
			String sql = "SELECT * FROM KEEP_TICKETS WHERE id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, ticketCode);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				KeepTicket ticket = new KeepTicket();
				ticket.setId(rs.getInt("id"));
				ticket.setUserId(rs.getString("user_id"));
				ticket.setExpiredAt(rs.getDate("expired_at"));
				return ticket;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
