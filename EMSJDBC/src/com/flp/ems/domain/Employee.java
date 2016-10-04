package com.flp.ems.domain;

import java.util.Date;
		
public class Employee implements Cloneable{
	static int counter=0;
	private Key employeeId;
	private String name;
	private int kinId;
	private String emailId;
	private String phoneNo;
	private String address;
	private Date dob;
	private Date doj;
	private Department department;
	private Project projects;
	private Role role;
	
	
	
	private Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee( Key employeeId,String name, String emailId,String phoneNo, String address, Date dob, Date doj,
			Department department, Project projects, Role role) {
		super();
		this.employeeId=employeeId;
		this.kinId=counter++;
		this.name = name;
		this.phoneNo = phoneNo;
		this.address = address;
		this.dob = dob;
		this.doj = doj;
		this.department = department;
		this.projects = projects;
		this.role = role;
		this.emailId=emailId;
	}

	public Key getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Department getDepartment() {
		return department;
	}public void setDepartment(Department department) {
		this.department = department;
	}
	public Project getProjects() {
		return projects;
	}
	
	public void setProjects(Project projects) {
		this.projects = projects;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public int getKinId() {
		return kinId;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		 Employee employee = new Employee();
		 	this.employeeId=employee.employeeId;
			this.name=employee.name;
			this.kinId=employee.kinId;
			this.emailId=employee.emailId;
			this.phoneNo=employee.phoneNo;
			this.address=employee.address;
			this.dob=employee.dob;
			this.doj=employee.doj;
			this.department=employee.department;
			this.projects=employee.projects;
			this.role=employee.role;
		 return employee;
	}
}
