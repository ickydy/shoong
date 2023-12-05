package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import data.SpendLog;
import model.vo.Message;

public class messageController {

	public boolean save(Message one) throws ClassNotFoundException {
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@13.125.229.23:1521:xe", "shoong",
				"oracle")) {
			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "INSERT INTO messages VALUES(SPEND_LOG_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, one.getUserId());
			pst.setDate(3, one.getSpendAt());
			pst.setInt(2, one.getAmt());
			pst.setString(4, one.getUseDesc());
			pst.setInt(5, one.getCategoryId());

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
