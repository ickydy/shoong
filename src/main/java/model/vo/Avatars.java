package model.vo;

public class Avatars {
	private int id;
	private String alt;
	private String imageUrl;
	
	
	public Avatars() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Avatars(int id, String alt, String imageUrl) {
		super();
		this.id = id;
		this.alt = alt;
		this.imageUrl = imageUrl;
	}

	
	
	
}