package com.flp.ems.domain;

public class Project {
	int id;
	String nameOfProject;

	public Project(int id,String nameOfProject) {
		this.nameOfProject=nameOfProject;
		this.id=id;
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
