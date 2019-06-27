/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yuconz;

import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rm631
 */
public class ReviewTest {
    
    public ReviewTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void assignReviewers() {
        Yuconz yu = new Yuconz();
        HrEmployee hr = new HrEmployee("abc121", "pass121", "Human Resources", "Director");
        String reviewee = "abc113";
        String reviewer1 = "abc120";
        String reviewer2 = "abc111";
        yu.setUser(hr);
        assertTrue(yu.assignReviewers(reviewee, reviewer1, reviewer2));
    }
    
    @Test
    public void revieweeCreateReview() {
        Yuconz yu = new Yuconz();
        Employee user = new Employee("abc113", "pass113", "Administration", "Employee");
        yu.setUser(user);
        ArrayList<String> achievements = new ArrayList<>();
        for(int i = 1; i <=3; i++) {
            achievements.add(Integer.toString(i));
            achievements.add("objective");
            achievements.add("Achievement");
        }
        Review review = new Review("abc113", "abc113", "abc111", "abc120", "Administration",
            "Employee", achievements, "Summary", "future", "comments", "salary increase", 
            "", "", "", "", "", "");
        assertTrue(yu.authoriseCreateReview());
        assertTrue(yu.createReview(review));
    }
    
    @Test
    public void reviewerCreateReview() {
        Yuconz yu = new Yuconz();
        Reviewer user = new Reviewer("abc120", "pass120", "Human Resources", "Reviewer");
        yu.setUser(user);
        assertFalse(yu.authoriseCreateReview());
    }
    
    /**
     * authoriseReadPast
     */
    @Test
    public void readPastReviews() {
        Yuconz yu = new Yuconz();
        Director user = new Director("abc111", "pass111", "Administration", "Director");
        yu.setUser(user);
        
        assertNotNull(yu.authoriseReadPastReview());
    }
    
    @Test
    public void readPastReviewAsHr() {
        Yuconz yu = new Yuconz();
        Director user = new Director("abc121", "pass121", "Human Resources", "Director");
        yu.setUser(user);
        
        assertNotNull(yu.authoriseReadPastReview());
    }
    
    @Test
    public void amendCurrentReview() {
        Yuconz yu = new Yuconz();
        Employee user = new Employee("abc113", "pass113", "Administration", "Employee");
        yu.setUser(user);
        
        assertNotNull(yu.authoriseModifyReview());
    }
}
