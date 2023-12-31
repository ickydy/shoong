package model.vo;

public class Avatar {
	private int id;
	private String alt;
	private String imgUrl;

	public Avatar() {

	}

	public Avatar(int id, String alt, String imgUrl) {
		this.id = id;
		this.alt = alt;
		this.imgUrl = imgUrl;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}