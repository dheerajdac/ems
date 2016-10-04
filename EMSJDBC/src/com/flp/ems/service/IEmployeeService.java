package com.flp.ems.service;

import java.util.HashMap;
import java.util.List;

import com.flp.ems.domain.Employee;

public interface IEmployeeService {
	public boolean AddEmployee(HashMap entry);
	public List<Employee> getAllEmployee();
	Employee SearchEmployee(int id);
	boolean RemoveEmployee(int id);
	boolean ModifyEmployee(int id, HashMap entry, int choice);
		
}
