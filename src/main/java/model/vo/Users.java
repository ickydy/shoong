package model.vo;

import java.sql.Date;

public class Users {
	 private int id;
	 private String password;
	 private Date birth ;
	 private String name;
	 private String country_id;
	 private String gender;
	 private int open_access;
	 
	public Users() {
		// TODO Auto-generated constructor stub
	}

	public Users(int id, String password, Date birth, String name, String country_id, String gender, int open_access) {
		super();
		this.id = id;
		this.password = password;
		this.birth = birth;
		this.name = name;
		this.country_id = country_id;
		this.gender = gender;
		this.open_access = open_access;
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

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getOpen_access() {
		return open_access;
	}

	public void setOpen_access(int open_access) {
		this.open_access = open_access;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
