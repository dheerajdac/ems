package com.flp.ems.domain;

public class Project {
	int id;
	String nameOfProject;
	String desc;
	int departmentId;


	public Project(int id,String nameOfProject) {
		this.nameOfProject=nameOfProject;
		this.id=id;
	}
	
	public Project(int id, String nameOfProject, String desc, int departmentId) {
		super();
		this.id = id;
		this.nameOfProject = nameOfProject;
		this.desc = desc;
		this.departmentId = departmentId;
	}

	public int getId() {
		return id;
	}

	public String getNameOfProject() {
		return nameOfProject;
	}

	public void setNameOfProject(String nameOfProject) {
		this.nameOfProject = nameOfProject;
	}
	
}
