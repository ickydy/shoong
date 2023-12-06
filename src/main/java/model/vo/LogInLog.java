package model.vo;

import java.sql.Date;

public class LogInLog {
	private int id;
	private String userId;
	private Date logAt;
	private String logFrom;
	
	public LogInLog() {
		// TODO Auto-generated constructor stub
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

	public Date getLogAt() {
		return logAt;
	}

	public void setLogAt(Date logAt) {
		this.logAt = logAt;
	}

	public String getLogFrom() {
		return logFrom;
	}

	public void setLogFrom(String logFrom) {
		this.logFrom = logFrom;
	}

	public LogInLog(int id, String userId, Date logAt, String logFrom) {
		super();
		this.id = id;
		this.userId = userId;
		this.logAt = logAt;
		this.logFrom = logFrom;
	}
	
	
	
}
