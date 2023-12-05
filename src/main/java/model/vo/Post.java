package model.vo;

import java.sql.Date;

public class Post {
private int id;
private String userId;
private String title;
private String contents;
private Date writeAt;
private int viewCount;

public Post() {
	// TODO Auto-generated constructor stub
}



public Post(int id, String userId, String title, String contents, Date writeAt, int viewCount) {
	super();
	this.id = id;
	this.userId = userId;
	this.title = title;
	this.contents = contents;
	this.writeAt = writeAt;
	this.viewCount = viewCount;
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

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
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

public int getViewCount() {
	return viewCount;
}

public void setViewCount(int viewCount) {
	this.viewCount = viewCount;
}



}
