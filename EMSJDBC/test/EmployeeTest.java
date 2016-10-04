import com.flp.ems.dao.EmployeeDaoImplForJdbc;
import com.flp.ems.domain.Department;
import com.flp.ems.domain.Employee;
import com.flp.ems.domain.Key;
import com.flp.ems.util.Validate;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {
	@Ignore
	@Test
	public void testEmployeeConstruction() {
		Key key = new Key("dhe-007");
		Department d1 =new Department(1,"department1");
		d1.addProject(1,"d1p1");
		d1.addProject(2,"d1p2");
		d1.addRoles(1,"d1r1");
		d1.addRoles(2,"d1r2");
		Date date= new Date();
		date.getTime();
		Employee e1 = new Employee(key,"dheeraj", "","9420026097", "pune", date, date, d1, d1.getProject(0), d1.getRole(0));
	}
	
	@Ignore
	@Test
	public void testValidateDate(){
		assertEquals(Validate.validateDate("18/01/1994"),true);
	}
	
	
	@Ignore
	@Test
	public void testValidateName(){	
		assertEquals(Validate.validateName("dheeraj"),true);
	}
	
	@Ignore
	@Test
	public void testValidateEmail(){
		assertEquals(Validate.validateEmail("dheraj.@gmail.com"),true);
	}

	@Ignore
	@Test
	public void testValidatePhone(){
		assertEquals(Validate.validatePhoneNo("9420026037"),true);
	}

	
	@Test
	public void testvalidateaddemployee(){
		EmployeeDaoImplForJdbc employeeDaoImplForJdbc =null;
		employeeDaoImplForJdbc = new EmployeeDaoImplForJdbc();
		Key key = new Key("dhe-007");
		Department d1 =new Department(1,"department1");
		d1.addProject(1,"d1p1");
		d1.addProject(2,"d1p2");
		d1.addRoles(1,"d1r1");
		d1.addRoles(2,"d1r2");
		Date date= new Date();
		date.getTime();
		Employee e1 = new Employee(key,"dheeraj","", "9420026097", "pune", date, date, d1, d1.getProject(0), d1.getRole(0));
		
		assertEquals(employeeDaoImplForJdbc.AddEmployee(e1),true);
	}

	@Ignore
	@Test
	public void validateSearch(){
        EmployeeDaoImplForJdbc employeeDaoImplForJdbc =null;
        employeeDaoImplForJdbc = new EmployeeDaoImplForJdbc();
        System.out.println(employeeDaoImplForJdbc.SearchEmployee(3).getName());
        assertEquals(Validate.validatePhoneNo("9420026037"),true);
    }

	
    @Test
    public void validateModifyemployee(){
        EmployeeDaoImplForJdbc employeeDaoImplForJdbc =null;
        employeeDaoImplForJdbc = new EmployeeDaoImplForJdbc();
        Key key = new Key("dhe-4");
        Department d1 =new Department(1,"department1");
        d1.addProject(1,"d1p1");
        d1.addProject(2,"d1p2");
        d1.addRoles(1,"d1r1");
        d1.addRoles(2,"d1r2");
        Date date= new Date();
        date.getTime();
        Employee e1 = new Employee(key,"dhe","", "9420029097", "pune", date, date, d1, d1.getProject(0), d1.getRole(0));
        employeeDaoImplForJdbc.ModifyEmployee(e1);
        assertEquals(Validate.validatePhoneNo("9420026037"),true);
    }
    
	@Ignore
    @Test
    public void validateRemove(){
        EmployeeDaoImplForJdbc employeeDaoImplForJdbc =null;
        employeeDaoImplForJdbc = new EmployeeDaoImplForJdbc();
        Key key = new Key("dhe-3");
        Department d1 =new Department(1,"department1");
        d1.addProject(1,"d1p1");
        d1.addProject(2,"d1p2");
        d1.addRoles(1,"d1r1");
        d1.addRoles(2,"d1r2");
        Date date= new Date();
        date.getTime();
        Employee e1 = new Employee(key,"dheeraj","", "9420026097", "pune", date, date, d1, d1.getProject(0), d1.getRole(0));


        assertEquals(employeeDaoImplForJdbc.RemoveEmployee(e1),true);
    }
}
