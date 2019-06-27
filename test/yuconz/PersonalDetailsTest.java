package yuconz;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ryan
 */
public class PersonalDetailsTest {
    
    @BeforeClass
    public static void setUpClass(){
    }
    
    @AfterClass
    public static void tearDownClass(){
    }
    
    @Before
    public void setUp(){
    }
    
    @After
    public void tearDown(){
    }
    
    /** Employee - create, modify & read **/
    
    @Test
    public void testEmployeeAuthCreate() {
        Yuconz yu = new Yuconz();
        Employee user = new Employee("abc113", "pass113", "Administration", "Employee");       
        user.setAuthLevel("Employee");
        yu.setUser(user);
        
        assertFalse(yu.authoriseCreatePersonal());
    }
    
    /**
     * Test for employee modifying their own PD file
     */
    @Test
    public void testEmployeeAuthModifyOwn() {
        Yuconz yu = new Yuconz();
        Employee user = new Employee("abc113", "pass113", "Administration", "Employee");       
        user.setAuthLevel("Employee");
        yu.setUser(user);
        
        assertTrue(yu.authoriseModifyPersonal("abc113"));
    }
    
    /**
     * Test for employee modifying file that isn't their own
     */
    @Test
    public void testEmployeeAuthModify() {
        Yuconz yu = new Yuconz();
        Employee user = new Employee("abc113", "pass113", "Administration", "Employee");       
        user.setAuthLevel("Employee");
        yu.setUser(user);
        
        assertFalse(yu.authoriseModifyPersonal("abc111"));
    }
    
    @Test
    public void testEmployeeAuthReadOwn() {
        Yuconz yu = new Yuconz();
        Employee user = new Employee("abc113", "pass113", "Administration", "Employee");       
        user.setAuthLevel("Employee");
        yu.setUser(user);
        
        assertTrue(yu.authoriseReadPersonal("abc113"));
    }
    
    @Test
    public void testEmployeeAuthRead() {
        Yuconz yu = new Yuconz();
        Employee user = new Employee("abc113", "pass113", "Administration", "Employee");       
        user.setAuthLevel("Employee");
        yu.setUser(user);
        
        assertFalse(yu.authoriseModifyPersonal("abc111"));
    }
    
    /** HR Employee - create, modify & read **/
    
    @Test
    public void testHrEmployeeAuthCreate() {
        Yuconz yu = new Yuconz();
        HrEmployee user = new HrEmployee("abc122", "pass122", "Human Resources", "HR Employee");       
        user.setAuthLevel("HR Employee");
        yu.setUser(user);
        
        assertTrue(yu.authoriseCreatePersonal());
    }
    
    @Test
    public void testHrEmployeeAuthModifyOwn() {
        Yuconz yu = new Yuconz();
        HrEmployee user = new HrEmployee("abc122", "pass122", "Human Resources", "HR Employee");       
        user.setAuthLevel("HR Employee");
        yu.setUser(user);
        
        assertTrue(yu.authoriseModifyPersonal("abc122"));
    }
    
    /**
     * If a member of HR authenticates as an employee,
     * they should not be able to modify
     */
    @Test
    public void testHrEmployeeAuthModifyOwnAsEmployee() {
        Yuconz yu = new Yuconz();
        Employee user = new Employee("abc122", "pass122", "Human Resources", "Employee");       
        user.setAuthLevel("Employee");
        yu.setUser(user);
        
        assertTrue(yu.authoriseModifyPersonal("abc122"));
    }
    
    @Test
    public void testHrEmployeeAuthModify() {
        Yuconz yu = new Yuconz();
        HrEmployee user = new HrEmployee("abc122", "pass122", "Human Resources", "HR Employee");       
        user.setAuthLevel("HR Employee");
        yu.setUser(user);
        
        assertTrue(yu.authoriseModifyPersonal("abc120"));
    }
    
    /**
     * If a member of HR authenticates as an employee,
     * they should not be able to modify
     */
    @Test
    public void testHrEmployeeAuthModifyAsEmployee() {
        Yuconz yu = new Yuconz();
        Employee user = new Employee("abc122", "pass122", "Human Resources", "Employee");       
        user.setAuthLevel("Employee");
        yu.setUser(user);
        
        assertFalse(yu.authoriseModifyPersonal("abc120"));
    }
    
    @Test
    public void testHrEmployeeAuthReadOwn() {
        Yuconz yu = new Yuconz();
        HrEmployee user = new HrEmployee("abc122", "pass122", "Human Resources", "HR Employee");       
        user.setAuthLevel("HR Employee");
        yu.setUser(user);
        
        assertTrue(yu.authoriseReadPersonal("abc122"));
    }
    
    @Test
    public void testHrEmployeeAuthReadOwnAsEmployee() {
        Yuconz yu = new Yuconz();
        HrEmployee user = new HrEmployee("abc122", "pass122", "Human Resources", "Employee");       
        user.setAuthLevel("Employee");
        yu.setUser(user);
        
        assertTrue(yu.authoriseReadPersonal("abc122"));
    }
    
    @Test
    public void testHrEmployeeAuthRead() {
        Yuconz yu = new Yuconz();
        HrEmployee user = new HrEmployee("abc122", "pass122", "Human Resources", "HR Employee");       
        user.setAuthLevel("HR Employee");
        yu.setUser(user);
        
        assertTrue(yu.authoriseReadPersonal("abc120"));
    }
    
    /**
     * If a member of HR authenticates as an employee,
     * they should not be able to modify
     */
    @Test
    public void testHrEmployeeAuthReadAsEmployee() {
        Yuconz yu = new Yuconz();
        HrEmployee user = new HrEmployee("abc122", "pass122", "Human Resources");       
        user.setAuthLevel("Employee");
        yu.setUser(user);
        
        assertFalse(yu.authoriseReadPersonal("abc120"));
    }
    
    /** Manager - create, modify & read **/
    
    @Test
    public void testManagerAuthCreate() {
        Yuconz yu = new Yuconz();
        Manager user = new Manager("abc112", "pass112", "Administration");       
        user.setAuthLevel("Manager");
        yu.setUser(user);
        
        assertFalse(yu.authoriseCreatePersonal());
    }
    
    @Test
    public void testHrManagerAuthCreate() {
        Yuconz yu = new Yuconz();
        Manager user = new Manager("abc121", "pass121", "Human Resources");       
        user.setAuthLevel("Manager");
        yu.setUser(user);
        
        assertTrue(yu.authoriseCreatePersonal());
    }
    
    @Test
    public void testHrManagerAuthModifyOwn() {
        Yuconz yu = new Yuconz();
        Manager user = new Manager("abc121", "pass121", "Human Resources");       
        user.setAuthLevel("Manager");
        yu.setUser(user);
        
        assertTrue(yu.authoriseModifyPersonal("abc121"));
    }
    
    @Test
    public void testManagerAuthModifyOwn() {
        Yuconz yu = new Yuconz();
        Manager user = new Manager("abc112", "pass112", "Administration");       
        user.setAuthLevel("Manager");
        yu.setUser(user);
        
        assertTrue(yu.authoriseModifyPersonal("abc112"));
    }
    
    @Test
    public void testManagerAuthModifyOwnAsEmployee() {
        Yuconz yu = new Yuconz();
        Manager user = new Manager("abc112", "pass112", "Administration");       
        user.setAuthLevel("Employee");
        yu.setUser(user);
        
        assertTrue(yu.authoriseModifyPersonal("abc112"));
    }
    
    @Test
    public void testHrManagerAuthModifyOwnAsHrEmployee() {
        Yuconz yu = new Yuconz();
        Manager user = new Manager("abc121", "pass121", "Human Resources");       
        user.setAuthLevel("HR Employee");
        yu.setUser(user);
        
        assertTrue(yu.authoriseModifyPersonal("abc121"));
    }
    
    @Test
    public void testManagerAuthModify() {
        Yuconz yu = new Yuconz();
        Manager user = new Manager("abc112", "pass112", "Administration");       
        user.setAuthLevel("Manager");
        yu.setUser(user);
        
        assertFalse(yu.authoriseModifyPersonal("abc120"));
    }
    
    @Test
    public void testManagerAuthModifyAsEmployee() {
        Yuconz yu = new Yuconz();
        Manager user = new Manager("abc112", "pass112", "Administration");        
        user.setAuthLevel("Employee");
        yu.setUser(user);
        
        assertFalse(yu.authoriseModifyPersonal("abc120"));
    }
    
    @Test
    public void testManagerAuthReadOwn() {
        Yuconz yu = new Yuconz();
        Manager user = new Manager("abc112", "pass112", "Administration");       
        user.setAuthLevel("Manager");
        yu.setUser(user);
        
        assertTrue(yu.authoriseReadPersonal("abc112"));
    }
    
    @Test
    public void testManagerAuthReadOwnAsEmployee() {
        Yuconz yu = new Yuconz();
        Manager user = new Manager("abc112", "pass112", "Administration");       
        user.setAuthLevel("Employee");
        yu.setUser(user);
        
        assertTrue(yu.authoriseReadPersonal("abc112"));
    }
    
    @Test
    public void testHrManagerAuthReadOwnAsHrEmployee() {
        Yuconz yu = new Yuconz();
        Manager user = new Manager("abc121", "pass121", "Human Resouces");      
        user.setAuthLevel("HR Employee");
        yu.setUser(user);
        
        assertTrue(yu.authoriseReadPersonal("abc121"));
    }
    
    @Test
    public void testManagerAuthRead() {
        Yuconz yu = new Yuconz();
        Manager user = new Manager("abc112", "pass112", "Administration");       
        user.setAuthLevel("Manager");
        yu.setUser(user);
        
        assertFalse(yu.authoriseReadPersonal("abc120"));
    }
    
    @Test
    public void testManagerAuthReadAsEmployee() {
        Yuconz yu = new Yuconz();
        Manager user = new Manager("abc112", "pass112", "Administration");       
        user.setAuthLevel("Employee");
        yu.setUser(user);
        
        assertFalse(yu.authoriseReadPersonal("abc120"));
    }
    
    @Test
    public void testHrManagerAuthReadAsHrEmployee() {
        Yuconz yu = new Yuconz();
        Manager user = new Manager("abc120", "pass120", "Human Resources");       
        user.setAuthLevel("HR Employee");
        yu.setUser(user);
        
        assertTrue(yu.authoriseReadPersonal("abc111"));
    }
    
    /** Director - create, modify & read **/
    @Test
    public void testDirectorAuthCreate() {
        Yuconz yu = new Yuconz();
        Director user = new Director("abc111", "pass111", "Administration");       
        user.setAuthLevel("Director");
        yu.setUser(user);
        
        assertFalse(yu.authoriseCreatePersonal());
    }
    
    @Test
    public void testHrDirectorAuthCreate() {
        Yuconz yu = new Yuconz();
        Director user = new Director("abc120", "pass120", "Human Resources");       
        user.setAuthLevel("Director");
        yu.setUser(user);
        
        assertTrue(yu.authoriseCreatePersonal());
    }
    
    @Test
    public void testDirectorAuthModifyOwn() {
        Yuconz yu = new Yuconz();
        Director user = new Director("abc111", "pass111", "Administration");       
        user.setAuthLevel("Director");
        yu.setUser(user);
        
        assertTrue(yu.authoriseModifyPersonal("abc111"));
    }
    
    @Test
    public void testDirectorAuthModify() {
        Yuconz yu = new Yuconz();
        Director user = new Director("abc111", "pass111", "Administration");       
        user.setAuthLevel("Director");
        yu.setUser(user);
        
        assertFalse(yu.authoriseModifyPersonal("abc120"));
    }
    
    @Test
    public void testHrDirectorAuthModify() {
        Yuconz yu = new Yuconz();
        Director user = new Director("abc120", "pass120", "Human Resources");       
        user.setAuthLevel("Director");
        yu.setUser(user);
        
        assertTrue(yu.authoriseModifyPersonal("abc111"));
    }
    
    @Test
    public void testDirectorAuthReadOwn() {
        Yuconz yu = new Yuconz();
        Director user = new Director("abc111", "pass111", "Administration");       
        user.setAuthLevel("Director");
        yu.setUser(user);
        
        assertTrue(yu.authoriseModifyPersonal("abc111"));
    }
    
    @Test
    public void testDirectorAuthRead() {
        Yuconz yu = new Yuconz();
        Director user = new Director("abc111", "pass111", "Administration");       
        user.setAuthLevel("Director");
        yu.setUser(user);
        
        assertFalse(yu.authoriseModifyPersonal("abc120"));
    }
    
    @Test
    public void testHrDirectorAuthRead() {
        Yuconz yu = new Yuconz();
        Director user = new Director("abc120", "pass120", "Human Resources");       
        user.setAuthLevel("Director");
        yu.setUser(user);
        
        assertTrue(yu.authoriseModifyPersonal("abc111"));
    }
    
}
