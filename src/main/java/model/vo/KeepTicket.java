package model.vo;

import java.sql.Date;

public class KeepTicket {
	int id;
	String userId;
	Date expiredAt;

	public KeepTicket() {
		super();
	}

	public KeepTicket(int id, String userId, Date expiredAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.expiredAt = expiredAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}

}
