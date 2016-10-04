package com.flp.ems.dao;
import com.flp.ems.domain.Department;
import com.flp.ems.domain.Employee;
import com.flp.ems.domain.Key;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
                Department d1 = new Department(1, "department1");
                d1.addProject(1, "d1p1");
                d1.addProject(2, "d1p2");
                d1.addRoles(1, "d1r1");
                d1.addRoles(2, "d1r2");
                Key key = new Key(Integer.parseInt(result.getString(1)),"dhee-");
                Employee e1 = new Employee(key, result.getString(2), result.getString(3), result.getString(4), result.getString(7), result.getDate(5), result.getDate(6), d1, d1.getProject(0), d1.getRole(0));
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
                    Department d1 = new Department(1, "department1");
                    d1.addProject(1, "d1p1");
                    d1.addProject(2, "d1p2");
                    d1.addRoles(1, "d1r1");
                    d1.addRoles(2, "d1r2");
                    Key key =  new Key(Integer.parseInt(result.getString(1)),"dhee-");
                    
                    e1 = new Employee(key, result.getString(2), result.getString(3), result.getString(4), result.getString(7), result.getDate(5), result.getDate(6), d1, d1.getProject(0), d1.getRole(0));
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

