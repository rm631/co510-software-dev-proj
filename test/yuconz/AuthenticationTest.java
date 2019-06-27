package yuconz;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rm631, ol61, mb859, ra466
 */
public class AuthenticationTest {
    
    public AuthenticationTest() {
        
    }
    
    /**
     * Called before the class is tested
     */
    @BeforeClass
    public static void setUpClass() {
        //Yuconz.testDataSetup();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Called before each @test
     */
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /** Employee **/
    @Test
    public void testAuthEmployeeAsEmployee() {
        Yuconz yu = new Yuconz();
        String username = "abc113";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '1', '3'};
        String role = "Employee";
        assertTrue(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthEmployeeAsHrEmployee() {
        Yuconz yu = new Yuconz();
        String username = "abc113";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '1', '3'};
        String role = "HR Employee";
        assertFalse(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthEmployeeAsManager() {
        Yuconz yu = new Yuconz();
        String username = "abc113";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '1', '3'};
        String role = "Manager";
        assertFalse(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthEmployeeAsDirector() {
        Yuconz yu = new Yuconz();
        String username = "abc113";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '1', '3'};
        String role = "Director";
        assertFalse(yu.login(username, password, role));
    }
    
    /** HR Employee **/
    @Test
    public void testAuthHrEmployeeAsEmployee() {
        Yuconz yu = new Yuconz();
        String username = "abc122";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '2', '2'};
        String role = "Employee";
        assertTrue(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthHrEmployeeAsHrEmployee() {
        Yuconz yu = new Yuconz();
        String username = "abc122";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '2', '2'};
        String role = "HR Employee";
        assertTrue(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthHrEmployeeAsManager() {
        Yuconz yu = new Yuconz();
        String username = "abc122";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '2', '2'};
        String role = "Manager";
        assertFalse(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthHrEmployeeAsDirector() {
        Yuconz yu = new Yuconz();
        String username = "abc122";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '2', '2'};
        String role = "Director";
        assertFalse(yu.login(username, password, role));
    }
    
    /**
     * Manager, where HrManager refers to the manager of HR
     * and we expect a different result to that of the other managers
     */
    @Test
    public void testAuthManagerAsEmployee() {
        Yuconz yu = new Yuconz();
        String username = "abc112";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '1', '2'};
        String role = "Employee";
        assertTrue(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthManagerAsHrEmployee() {
        Yuconz yu = new Yuconz();
        String username = "abc112";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '1', '2'};
        String role = "HR Employee";
        assertFalse(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthHrManagerAsHrEmployee() {
        Yuconz yu = new Yuconz();
        String username = "abc121";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '2', '1'};
        String role = "HR Employee";
        assertTrue(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthManagerAsDirector() {
        Yuconz yu = new Yuconz();
        String username = "abc112";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '1', '2'};
        String role = "HR Employee";
        assertFalse(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthHrManagerAsDirector() {
        Yuconz yu = new Yuconz();
        String username = "abc120";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '2', '0'};
        String role = "Director";
        assertTrue(yu.login(username, password, role));
    }
    
    /**
     * Director, where HrDirector refers to the director of HR
     * and we expect a different result to that of the other managers
     */
    @Test
    public void testAuthDirectorAsEmployee() {
        Yuconz yu = new Yuconz();
        String username = "abc111";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '1', '1'};
        String role = "Employee";
        assertTrue(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthDirectorAsHrEmployee() {
        Yuconz yu = new Yuconz();
        String username = "abc111";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '1', '1'};
        String role = "HR Employee";
        assertFalse(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthHrDirectorAsHrEmployee() {
        Yuconz yu = new Yuconz();
        String username = "abc120";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '2', '0'};
        String role = "HR Employee";
        assertTrue(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthDirectorAsManager() {
        Yuconz yu = new Yuconz();
        String username = "abc111";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '1', '1'};
        String role = "Manager";
        assertFalse(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthHrDirectorAsManager() {
        Yuconz yu = new Yuconz();
        String username = "abc120";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '2', '0'};
        String role = "Manager";
        assertTrue(yu.login(username, password, role));
    }
    
    @Test
    public void testAuthDirectorAsDirector() {
        Yuconz yu = new Yuconz();
        String username = "abc111";
        char[] password = new char[] {'p', 'a', 's', 's', '1', '1', '1'};
        String role = "Director";
        assertTrue(yu.login(username, password, role));
    }
    
}
