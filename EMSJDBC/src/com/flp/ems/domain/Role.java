package com.flp.ems.domain;

public class Role {
	int id;
	String nameOfRole;
	String desc;
	

	public Role(int id, String nameOfRole, String desc) {
		super();
		this.id = id;
		this.nameOfRole = nameOfRole;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public Role(int id, String nameOfRole) {
		super();
		this.id=id;
		this.nameOfRole = nameOfRole;
	}
	
	

	public String getNameOfRole() {
		return nameOfRole;
	}

	

	public void setNameOfRole(String nameOfRole) {
		this.nameOfRole = nameOfRole;
	}

}
