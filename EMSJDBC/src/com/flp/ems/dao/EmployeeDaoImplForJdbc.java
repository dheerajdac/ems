package com.flp.ems.dao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.flp.ems.domain.Department;
import com.flp.ems.domain.Employee;
import com.flp.ems.domain.Key;
import com.flp.ems.domain.Project;
import com.flp.ems.domain.Role;

/**
 * Created by dheeraj on 10/2/2016.
 */
public class EmployeeDaoImplForJdbc implements IemployeeDao{
    Properties prop;
    FileInputStream fin;
    Connection dbconnection;
    public EmployeeDaoImplForJdbc(){
        try {
			prop = new Properties();
			fin = new FileInputStream("database.properties");
			prop.load(fin);
			//String driver=prop.getProperty("jdbc.driver");;
			//Class.forName(driver);
			String url=prop.getProperty("jdbc.url");
			dbconnection= DriverManager.getConnection(url);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Connection sucessfull"+(dbconnection!=null));
    }

    @Override
    public boolean AddEmployee(Employee employee)  {

        String insertQuery=prop.getProperty("jdbc.query.insertEmployee");
            try(PreparedStatement preparedStatement = dbconnection.prepareStatement(insertQuery)){
                //preparedStatement.setString(1, (employee.getEmployeeId().getStr()+"-"+String.valueOf(employee.getEmployeeId().getI())));
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
        try (Statement selectStatement = dbconnection.createStatement()) {
            String selectQuery = prop.getProperty("jdbc.query.select");
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
            Employee e1=null;
            String selectQuery = prop.getProperty("jdbc.query.search");
            try(PreparedStatement preparedStatement = dbconnection.prepareStatement(selectQuery)){
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
        return e1;
    }

        
    @Override
    public boolean RemoveEmployee(Employee employee) {
        String deleteQuery = prop.getProperty("jdbc.query.delete");
        try(PreparedStatement preparedStatement = dbconnection.prepareStatement(deleteQuery)){
            preparedStatement.setString(1,String.valueOf(employee.getEmployeeId().getI()));
            preparedStatement.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
            return false;
    }

    @Override
    public boolean ModifyEmployee(Employee employee) {
        String updateQuery=prop.getProperty("jdbc.query.update");
        try(PreparedStatement preparedStatement = dbconnection.prepareStatement(updateQuery)){
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
            String selectQuery = prop.getProperty("jdbc.query.searchDepartment");
            try(PreparedStatement preparedStatement = dbconnection.prepareStatement(selectQuery)){
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
            String selectQuery = prop.getProperty("jdbc.query.searchProject");
            try(PreparedStatement preparedStatement = dbconnection.prepareStatement(selectQuery)){
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
         String selectQuery = prop.getProperty("jdbc.query.searchRole");
         try(PreparedStatement preparedStatement = dbconnection.prepareStatement(selectQuery)){
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
    
    

    private java.util.Date formatDate(String startDateString ){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = null;
        try {
            date = df.parse(startDateString);
            df.format(date);
        } catch (ParseException e) {
        }
        return date;
    }
}

