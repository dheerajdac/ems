package com.flp.ems.dao;

import com.flp.ems.domain.Employee;

import java.util.List;

public interface IemployeeDao {
	public boolean AddEmployee(Employee employee);
	public List<Employee> getAllEmployee();
	Employee SearchEmployee(int id);
	boolean RemoveEmployee(Employee employee);
	boolean ModifyEmployee(Employee employee);
	
	
}
