package model.vo;

import java.sql.Date;

public class friend {
	int id;
	String userId;
	String friendId;
	int confirmed;
	Date confirmedAt;

	public friend() {
		super();
	}

	public friend(int id, String userId, String friendId, int confirmed, Date confirmedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.friendId = friendId;
		this.confirmed = confirmed;
		this.confirmedAt = confirmedAt;
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
		return confirmedAt;
	}

	public void setConfirmedAt(Date confirmedAt) {
		this.confirmedAt = confirmedAt;
	}

}
