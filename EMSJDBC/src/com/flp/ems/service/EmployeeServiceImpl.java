package com.flp.ems.service;

import com.flp.ems.dao.EmployeeDaoImplForJdbc;
import com.flp.ems.dao.EmployeeDaoImplForList;
import com.flp.ems.domain.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService{
	
	//EmployeeDaoImplForList employeeDaoImplForList = new EmployeeDaoImplForList();
	EmployeeDaoImplForJdbc employeeDaoImplForJdbc = new EmployeeDaoImplForJdbc();
	
	
	@Override
	public boolean AddEmployee(HashMap entry) {
		String email=(String) entry.get(0);
		String name=(String) entry.get(1);
		String phoneNo=(String) entry.get(2);
		String address=(String) entry.get(3);
		Date dob=(Date) entry.get(4);
		Date doj=(Date) entry.get(5);
		Department department= (Department) entry.get(6);
		Project project=(Project) entry.get(7);
		Role role=(Role) entry.get(8);
		Employee employee = new Employee(null,name,email,phoneNo, address, dob, doj, department, project, role);
		//check if employee exists
		return employeeDaoImplForJdbc.AddEmployee(employee);

	}
	
	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> employee_List = employeeDaoImplForJdbc.getAllEmployee();
		return employee_List;
	}
	
	@Override
	public boolean ModifyEmployee(int id, HashMap entry, int choice) {
		Employee employee = employeeDaoImplForJdbc.SearchEmployee(id);
		if(employee!=null){
			switch (choice) {
			case 1:
				employee.setName((String) entry.get(choice));
				break;
			case 2:
				employee.setPhoneNo((String) entry.get(choice));
				break;
			case 3:
				employee.setAddress((String) entry.get(choice));
				break;
			case 4:
				employee.setDob((Date) entry.get(choice));
				break;
			case 5:
				employee.setDoj((Date) entry.get(choice));
				break;
			case 6:
				employee.setDepartment((Department) entry.get(choice));
				break;
			case 7:
				employee.setProjects((Project) entry.get(choice));;
				break;
			case 8:
				employee.setRole((Role) entry.get(choice));
				break;
			default:
				break;
			}
			employeeDaoImplForJdbc.ModifyEmployee(employee);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	@Override
	public boolean RemoveEmployee(int id) {
		Employee employee =SearchEmployee(id);
		if(employee!=null){
			if(employeeDaoImplForJdbc.RemoveEmployee(employee))
				return true;
		}
			return false;
	}
	
	@Override
	public Employee SearchEmployee(int id) {
		Employee employee=employeeDaoImplForJdbc.SearchEmployee(id);
		return employee;
	}

}
