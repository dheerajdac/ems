package com.flp.ems.dao;

import com.flp.ems.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImplForList implements IemployeeDao{
	
	List<Employee> employeeList = new ArrayList<Employee>();
	

	@Override
	public boolean AddEmployee(Employee employee) {
		employeeList.add(employee);
		return true;
	}
	
	@Override
	public List<Employee> getAllEmployee() {
		return employeeList;
	}
	
	@Override
	public boolean ModifyEmployee(Employee employee) {
			employeeList.set(employeeList.indexOf(SearchEmployee(employee.getKinId())),employee);
			return true;
	}
	
	@Override
	public boolean RemoveEmployee(Employee employee) {
				employeeList.remove(employeeList.indexOf(employee));
				return true;
	}
	
	@Override
	public Employee SearchEmployee(int id) {
		for(Employee e:employeeList){
			if(id==e.getKinId()){
				return e;
			}
		}
		return null;
	}
	
	
}
