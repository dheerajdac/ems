package com.flp.ems.view;

import com.flp.ems.domain.Department;
import com.flp.ems.domain.Employee;
import com.flp.ems.domain.Key;
import com.flp.ems.service.EmployeeServiceImpl;
import com.flp.ems.util.Validate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

;



public class UserInteraction {

	EmployeeServiceImpl service =new EmployeeServiceImpl();
	
	
	private Date formatDate(String startDateString ){
	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
	    Date date = null;
	    try {
	        date = df.parse(startDateString);
	        df.format(date);
	    } catch (ParseException e) {
	    }
	    return date;
	}
	
	
	public void AddEmployee() {
		Scanner sc =new Scanner(System.in);
		
		System.out.println("Enter the email-id");
		String email=null;
		while(!Validate.validateEmail(email)){
			email=sc.next();
		}
		
		
		System.out.println("Enter the name of the new employee");
		String name=null;
		while(!Validate.validateName(name)){
			name=sc.next();
		}
		
		
		System.out.println("Enter the phone no. of the new employee");
		String phoneNo=null;
		while(!Validate.validatePhoneNo(phoneNo)){
			phoneNo=sc.next();
		}
		
		
		System.out.println("Enter the Address of the new employee");
		String address =null;
		while(!Validate.validateName(address)){
			address=sc.next();
		}
		
		
		Department d1 =new Department(1,"department1");
		d1.addProject(1,"d1p1");
		d1.addProject(2,"d1p2");
		d1.addRoles(1,"d1r1");
		d1.addRoles(2,"d1r2");
		
		String str;
		System.out.println("Enter the date of birth in the following format dd/MM/yyyy");
		
		str=null;
		Date dob = null;
		while(!Validate.validateDate(str)){
			str=sc.next();
		}
		dob= formatDate(str);
		
		
		System.out.println("Enter the date of birth in the following format dd/MM/yyyy");
		Date doj= null;
		str=null;
		while(!Validate.validateDate(str)){
			str=sc.next();
		}
		doj= formatDate(str);
		
		
		
		//Creating hashmap  for Employee
		HashMap entry= new HashMap<>();
		entry.put(0, email);
		entry.put(1, name);
		entry.put(2,phoneNo);
		entry.put(3, address);
		entry.put(4, dob);
		entry.put(5, doj);
		entry.put(6, d1);
		entry.put(7, d1.getProject(0));
		entry.put(8, d1.getRole(0));
		if(service.AddEmployee(entry))
			System.out.println("Employee added successfully");
		else
			System.out.println("Employee already exists");
	}
	
	
	public void ModifyEmployee() {
		int choice;
		Scanner sc =new Scanner(System.in);
		boolean flag=true;
		System.out.println("Enter the Empoyee id");
		int id = sc.nextInt();
		Employee employee=service.SearchEmployee(id);
		if(employee==null){
			
			System.out.println("Employee not found");
		}
		else{
			while(flag){
				Department d1 =new Department(1,"department1");
				d1.addProject(1,"d1p1");
				d1.addProject(2,"d1p2");
				d1.addRoles(1,"d1r1");
				d1.addRoles(2,"d1r2");
				System.out.println("Enter the property to be modified");
				System.out.println("1. Name");
				System.out.println("2. Phone no");
				System.out.println("3. address");
				System.out.println("4. Date of birth");
				System.out.println("5. Date of join");
				System.out.println("6. Department");
				System.out.println("7. Project");
				System.out.println("8. Role");
				System.out.println("0. Exit");
				HashMap entry= new HashMap<>();
				choice=sc.nextInt();
				String str;
				switch (choice) {
				case 1:
					String name=sc.next();
					entry.put(1,name);
					break;
				case 2:
					String phoneNo=sc.next();
					entry.put(2,phoneNo);
					break;
				case 3:
					String address=sc.next();
					entry.put(3,address);
					break;
				case 4:
					System.out.println("Enter the date of birth in the following format dd/MM/yyyy");
					str=null;
					Date dob = null;
					while(!Validate.validateDate(str)){
						str=sc.next();
					}
					dob= formatDate(str);
					entry.put(4,dob);	
					break;
				case 5:
					System.out.println("Enter the date of birth in the following format dd/MM/yyyy");
					str=null;
					Date doj=null;
					while(!Validate.validateDate(str)){
						str=sc.next();
					}
					doj= formatDate(str);
					entry.put(5,doj);
					break;
				case 6:
					entry.put(6, d1);
					break;
				case 7:
					entry.put(7, d1.getProject(0));
					break;
				case 8:
					entry.put(8, d1.getRole(0));
					break;
				case 0:
					flag=false;
					break;
				default:
					break;
				}
				if(service.ModifyEmployee(id,entry,choice))
					System.out.println("Employee updated successfully");
				else
					System.out.println("Falied to update employee check employee id");
			}
		}
	}
	
	
	
	public void RemoveEmployee() {
		int id;
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter the employee Id");
		id=sc.nextInt();
		if(service.RemoveEmployee(id)==true){
			System.out.println("Deleted successfully");
		}
		else
			System.out.println("Could not delete the employee");	
	}
	
	
	public void SearchEmployee() {
		int id;
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter the employee Id");
		id=sc.nextInt();
		Employee employee=service.SearchEmployee(id);
		if(employee==null){
			System.out.println("Employee not found");
		}
		else{
			System.out.println("Employee found");
		}
	}
	
	public void getAllEmployee() {
		List<Employee> employee_List = service.getAllEmployee();
		for(Employee e:employee_List)
			System.out.println(e.getName()+"   "+e.getEmployeeId().getI()+"    "+ e.getPhoneNo());
	}

}
