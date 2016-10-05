package com.flp.ems.domain;

import java.util.ArrayList;
import java.util.List;

public class Department {
	int id;
	String nameOfDepartment;
	List<Role> role = new ArrayList<Role>();
	List<Project> projects = new ArrayList<Project>();
	String desc;

	public int getId() {
		return id;
	}

	public Department(int id, String nameOfDepartment,String desc) {
		super();
		this.id=id;
		this.nameOfDepartment = nameOfDepartment;
		this.desc =desc;
	}

	public String getNameOfDepartment() {
		return nameOfDepartment;
	}
	
	public void setNameOfDepartment(String nameOfDepartment) {
		this.nameOfDepartment = nameOfDepartment;
	}
	
	public Project getProject(int index){
		return projects.get(index);
	}
	
	public Role getRole(int index){
		return role.get(index);
	}
	
	public void addProject(int id,String nameOfProject){
		projects.add(new Project(id,nameOfProject));
	}
	
	public void  addRoles(int id,String nameOfRole){
		role.add(new Role(id,nameOfRole));
	}
	

}
