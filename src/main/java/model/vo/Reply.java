package model.vo;

import java.sql.Date;

public class Reply {

	private int id;
	private String userId;
	private String contents;
	private Date writeAt;
	private int PostId;

	public Reply() {
	}

	public Reply(int id, String userId, String contents, Date writeAt, int postId) {
		super();
		this.id = id;
		this.userId = userId;
		this.contents = contents;
		this.writeAt = writeAt;
		PostId = postId;
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

	public int getPostId() {
		return PostId;
	}

	public void setPostId(int postId) {
		PostId = postId;
	}
	
	
	
	
	
}

	