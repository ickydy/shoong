package model.vo;

import java.sql.Date;

public class Friend {
	private int id;
	private String userId;
	private String friendId;
	private int confirmed;
	private Date confirmAt;

	public Friend() {
		super();
	}

	public Friend(int id, String userId, String friendId, int confirmed, Date confirmedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.friendId = friendId;
		this.confirmed = confirmed;
		this.confirmAt = confirmedAt;
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

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public int getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	public Date getConfirmedAt() {
		return confirmAt;
	}

	public void setConfirmedAt(Date confirmedAt) {
		this.confirmAt = confirmedAt;
	}

}
