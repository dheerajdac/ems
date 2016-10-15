package com.flp.ems.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.flp.ems.domain.Department;
import com.flp.ems.domain.Employee;
import com.flp.ems.domain.Key;
import com.flp.ems.domain.Project;
import com.flp.ems.domain.Role;

/**
 * Created by dheeraj on 10/2/2016.
 */


@Repository
public class EmployeeDaoImplForJdbc implements IemployeeDao{
    Connection dbconnection;
    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    DataSource dataSource;
     
    @Autowired
    public void setDataSource(DataSource dataSource) {
    	if(dataSource!=null)
    		System.out.println("datasource");
		this.dataSource = dataSource;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
		try {
			dbconnection= dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    public EmployeeDaoImplForJdbc(){
    	System.out.println("creating jdbc");
    
    }
    
    

    @Override
    public boolean AddEmployee(Employee employee)  {

        String insertQuery= "INSERT INTO `employee` (`name`, `email_id`, `phone_number`, `address`, `dateOfBirth`, `dateOfJoining`, `department_id`, `project_id`, `role_id`) VALUES (?,?,?,?,?,?,?,?,?)";
            try{
                //preparedStatement.setString(1, (employee.getEmployeeId().getStr()+"-"+String.valueOf(employee.getEmployeeId().getI())));
            	PreparedStatement preparedStatement = dbconnection.prepareStatement(insertQuery);
            	preparedStatement.setString(1, employee.getName());
                preparedStatement.setString(2, employee.getEmailId());
                preparedStatement.setString(3, employee.getPhoneNo());
                preparedStatement.setString(4, employee.getAddress());
                preparedStatement.setDate(5, new Date(employee.getDob().getTime()));
                preparedStatement.setDate(6, new Date(employee.getDoj().getTime()));
                preparedStatement.setString(7, String.valueOf(employee.getDepartment().getId()));
                preparedStatement.setString(8, String.valueOf(employee.getProjects().getId()));
                preparedStatement.setString(9, String.valueOf(employee.getRole().getId()));
                preparedStatement.executeUpdate();
                return true;
            }
         catch (SQLException e) {
        }
        return false;
    }

//date convert
    @Override
    public List<Employee> getAllEmployee() {
    	
    	
        List<Employee> employeeList = new ArrayList<Employee>();
        try{
        	Statement selectStatement = dbconnection.createStatement();
            String selectQuery = "select * from `test`.`employee`";
            ResultSet result;
            result = selectStatement.executeQuery(selectQuery);
            while (result.next()) {
                Department department = searchDepartment(result.getInt(8));
                Project project = SearchProject(result.getInt(9));
                Role role = SearchRole(result.getInt(10));
                Key key = new Key(Integer.parseInt(result.getString(1)),"dhee-");
                Employee e1 = new Employee(key, result.getString(2), result.getString(3), result.getString(4), result.getString(7), result.getDate(5), result.getDate(6), department, project,role);
                employeeList.add(e1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
    
    
    @Override
    public Employee SearchEmployee(int id) {
            Employee e1=new Employee();
            String selectQuery = "select * from `test`.`employee` where id = :id";
            /*try{
            	PreparedStatement preparedStatement = dbconnection.prepareStatement(selectQuery);
                preparedStatement.setString(1,String.valueOf(id));
                ResultSet result;
                result = preparedStatement.executeQuery();
                while (result.next()) {
                    Department department = searchDepartment(result.getInt(8));
                    Project project = SearchProject(result.getInt(9));
                    Role role = SearchRole(result.getInt(10));
                    Key key =  new Key(Integer.parseInt(result.getString(1)),"dhee-");
                    
                    e1 = new Employee(key, result.getString(2), result.getString(3), result.getString(4), result.getString(7), result.getDate(5), result.getDate(6), department, project,role);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return e1;*/
        
        
        BeanPropertySqlParameterSource sqlParameterSource;
		sqlParameterSource = new BeanPropertySqlParameterSource(id);
		Map row=namedParameterJdbcTemplate.queryForMap(selectQuery, sqlParameterSource);
		 Key key =  new Key((int)row.get("id"),"dhee-");
		e1.setEmployeeId(key);
		e1.setName((String) row.get("name"));
		e1.setEmailId((String) row.get("email_id"));
		e1.setPhoneNo((String) row.get("phone_number"));
		e1.setAddress((String) row.get("address"));
		e1.setDob((java.util.Date) row.get("dateOfBirth"));
		e1.setDoj((java.util.Date) row.get("dateOfJoining"));
		e1.setDepartment(searchDepartment((int) row.get("department_id")));
		e1.setProjects(SearchProject((int) row.get("project_id")));
		e1.setRole(SearchRole((int) row.get("role_id")));
		//namedParameterJdbcTemplate.(selectQuery, sqlParameterSource);
		return e1;
    }

        
    @Override
    public boolean RemoveEmployee(Employee employee) {
        String deleteQuery = "DELETE FROM `employee` WHERE id = :i";
        BeanPropertySqlParameterSource sqlParameterSource;
		sqlParameterSource = new BeanPropertySqlParameterSource(employee.getEmployeeId());		
		if(namedParameterJdbcTemplate.update(deleteQuery, sqlParameterSource)>0)
			return true;
		else
			return false;
    }

    @Override
    public boolean ModifyEmployee(Employee employee) {
        String updateQuery= "UPDATE `employee` SET `name`=?,`email_id`=?,`phone_number`=?,`address`=?,`dateOfBirth`=?,`dateOfJoining`=?,`department_id`=?,`project_id`=?,`role_id`=? WHERE id =?";
        try{
        	PreparedStatement preparedStatement = dbconnection.prepareStatement(updateQuery);
           // preparedStatement.setString(1, String.valueOf(employee.getKinId()));
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmailId());
            preparedStatement.setString(3, employee.getPhoneNo());
            preparedStatement.setString(4, employee.getAddress());
            preparedStatement.setDate(5, new Date(employee.getDob().getTime()));
            preparedStatement.setDate(6, new Date(employee.getDoj().getTime()));
            preparedStatement.setString(7, String.valueOf(employee.getDepartment().getId()));
            preparedStatement.setString(8, String.valueOf(employee.getProjects().getId()));
            preparedStatement.setString(9, String.valueOf(employee.getRole().getId()));
            preparedStatement.setString(10, String.valueOf(employee.getEmployeeId().getI()));
            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public Department searchDepartment(int id) {
            Department department=null;
            String selectQuery = "select * from `test`.`department` where id = ?";
            try{
            	PreparedStatement preparedStatement = dbconnection.prepareStatement(selectQuery);
                preparedStatement.setString(1,String.valueOf(id));
                ResultSet result;
                result = preparedStatement.executeQuery();
                while (result.next()) {
                    department = new Department(result.getInt(1), result.getString(2),result.getString(3));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return department;
    }
    
    
    
    public Project SearchProject(int id) {
            Project project=null;
            String selectQuery = "select * from `test`.`project` where id = ?";
            try{
            	PreparedStatement preparedStatement = dbconnection.prepareStatement(selectQuery);
                preparedStatement.setString(1,String.valueOf(id));
                ResultSet result;
                result = preparedStatement.executeQuery();
                while (result.next()) {
                	project= new Project(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return project;
    }
    
    
    
    public Role SearchRole(int id) {
    	 Role role=null;
         String selectQuery = "select * from `test`.`role` where id = ?";
         try{
        	 PreparedStatement preparedStatement = dbconnection.prepareStatement(selectQuery);
             preparedStatement.setString(1,String.valueOf(id));
             ResultSet result;
             result = preparedStatement.executeQuery();
             while (result.next()) {
                 role = new Role(result.getInt(1), result.getString(2),result.getString(3));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     return role;
    }
    
    
}

