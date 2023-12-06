package model.vo;

import java.sql.Date;

public class Friend {//주석달기
	private int id;
	private String userId;
	private String friendId;
	private int confirmed;
	private Date confirmAt;
	private int spam;

	private User user;

	public Friend() {
		super();
	}

	public Friend(int id, String userId, String friendId, int confirmed, Date confirmAt, int spam) {
		super();
		this.id = id;
		this.userId = userId;
		this.friendId = friendId;
		this.confirmed = confirmed;
		this.confirmAt = confirmAt;
		this.spam = spam;
	}

	public Friend(int id, String userId, String friendId, int confirmed, Date confirmAt, int spam, User user) {
		super();
		this.id = id;
		this.userId = userId;
		this.friendId = friendId;
		this.confirmed = confirmed;
		this.confirmAt = confirmAt;
		this.spam = spam;
		this.user = user;
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

	public Date getConfirmAt() {
		return confirmAt;
	}

	public void setConfirmAt(Date confirmAt) {
		this.confirmAt = confirmAt;
	}

	public int getSpam() {
		return spam;
	}

	public void setSpam(int spam) {
		this.spam = spam;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
