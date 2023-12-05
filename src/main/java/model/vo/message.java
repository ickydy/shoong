package model.vo;

import java.sql.Date;

public class message {

	int id;
	String userId;
	String friendId;
	String contents;
	Date writeAt;
	int viewStatus;

	public message() {
		super();
	}

	public message(int id, String userId, String friendId, String contents, Date writeAt, int viewStatus) {
		super();
		this.id = id;
		this.userId = userId;
		this.friendId = friendId;
		this.contents = contents;
		this.writeAt = writeAt;
		this.viewStatus = viewStatus;
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

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getWriteAt() {
		return writeAt;
	}

	public void setWriteAt(Date writeAt) {
		this.writeAt = writeAt;
	}

	public int getViewStatus() {
		return viewStatus;
	}

	public void setViewStatus(int viewStatus) {
		this.viewStatus = viewStatus;
	}

}
