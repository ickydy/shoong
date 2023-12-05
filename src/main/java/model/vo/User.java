package model.vo;

import java.sql.Date;

public class User {
	private String id;
	private String password;
	private Date birth;
	private String name;
	private String countryId;
	private String gender;
	private int openAccess;
	private int avatarId;
	private Avatar avatars;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String id, String password, Date birth, String name, String countryId, String gender, int openAccess,
			int avatarId) {
		super();
		this.id = id;
		this.password = password;
		this.birth = birth;
		this.name = name;
		this.countryId = countryId;
		this.gender = gender;
		this.openAccess = openAccess;
		this.avatarId = avatarId;
	}
	
	public User(String id, String password, Date birth, String name, String countryId, String gender, int openAccess,
			int avatarId, Avatar avatars) {
		super();
		this.id = id;
		this.password = password;
		this.birth = birth;
		this.name = name;
		this.countryId = countryId;
		this.gender = gender;
		this.openAccess = openAccess;
		this.avatarId = avatarId;
		this.avatars = avatars;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getOpenAccess() {
		return openAccess;
	}

	public void setOpenAccess(int openAccess) {
		this.openAccess = openAccess;
	}

	public int getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(int avatarId) {
		this.avatarId = avatarId;
	}
	public Avatar getAvatars() {
		return avatars;
	}

	public void setAvatars(Avatar avatars) {
		this.avatars = avatars;
	}
	

}
