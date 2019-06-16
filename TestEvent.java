
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestEvent.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestEvent
{
    /**
     * Default constructor for test class TestEvent
     */
    public TestEvent()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    //Testing the compareTo method in the event class for arrival case BC 1 
    @Test 
    public void arrtestcompareToBC1() { 
        int name1 = 1 ; 
        int name2 = 2 ; 
        Customer s = new Customer(3600, name1); 
        Customer p = new Customer(7500, name2); 

        Event a = new Event(s,3600,1, name1) ;
        Event b = new Event(p,7500,1, name2) ; 

        int the_ans = a.compareTo(b) ; 
        int tru_ans = -1 ;

        assertEquals(the_ans,tru_ans);
    } 

    //Testing the compareTo method in the event class for arrival case BC 2
    @Test 
    public void arrtestcompareToBC2() {
        int name1 = 1 ; 
        int name2 = 2 ; 
        Customer s = new Customer(7600, name1); 
        Customer p = new Customer(3600, name2); 

        Event a = new Event(s,7600,1, name1) ;
        Event b = new Event(p,3600,1, name2) ; 

        int the_ans = a.compareTo(b) ; 
        int tru_ans = 1 ;

        assertEquals(the_ans,tru_ans);

    }

    //Testing the compareTo method in the event class for arrival case BC 3
    @Test 
    public void arrtestcompareToBC3() {
        int name1 = 1 ; 
        int name2 = 2 ; 
        Customer s = new Customer(3600, name1); 
        Customer p = new Customer(3600, name2); 

        Event a = new Event(s,3600,1, name1) ;
        Event b = new Event(p,3600,1, name2) ; 

        int the_ans = a.compareTo(b) ; 
        int tru_ans = 0 ;

        assertEquals(the_ans,tru_ans);

    }

    //Testing the compareTo method in the event class for departure case BC  
    @Test 
    public void departuretestcompareTo() {
        int name1 = 1 ; 
        int name2 = 2 ; 
        Customer s = new Customer(3600, name1); 
        Customer p = new Customer(7500, name1 ); 

        Event a = new Event(s,3600,2, name1) ;
        Event b = new Event(p,7500,2, name2) ; 

        int the_ans = a.compareTo(b) ; 
        int tru_ans = -1 ;

        assertEquals(the_ans,tru_ans);
    } 

    //Testing the Depart Time method 
    @Test 
    public void departimeTest() {
        int current_time = 1600 ; 
        int time_served= 120 ; 

        Event a = new Event() ; // Creating an instacne of event 

        int the_ans= a.departTime(current_time,time_served); 
        int tru_ans= current_time + time_served; 

        assertEquals(the_ans,tru_ans);
    }

    //Testing the Wait Time method 
    @Test 
    public void waittimeTest() {
        int current_time = 11600 ;
        int name1 = 1; 
        int time = 9600; 
        
        Customer s = new Customer(3600, name1); // Creating an instance of customer 
        Event a = new Event (s,time,1, name1) ; // Creating an instance of Event
        
        int the_ans = a.waitTime(current_time, a.getTime()); 
        int tru_ans = current_time - time; 
        
         assertEquals(the_ans,tru_ans);

    }
   

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
