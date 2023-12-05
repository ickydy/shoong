package model.vo;

import java.sql.Date;

public class Users {
	private int id;
	private String password;
	private Date birth;
	private String name;
	private String countryId;
	private String gender;
	private int openAccess;

	public Users() {
		// TODO Auto-generated constructor stub
	}

	public Users(int id, String password, Date birth, String name, String countryId, String gender, int open_access) {
		super();
		this.id = id;
		this.password = password;
		this.birth = birth;
		this.name = name;
		this.countryId = countryId;
		this.gender = gender;
		this.openAccess = open_access;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

}
