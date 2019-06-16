
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class TestCoffeeShop.
 *
 * @author  (Irwin Frimpong)
 * @version (10/12/18)
 */
public class TestCoffeeShop
{
    /**
     * Default constructor for test class TestCoffeeShop
     */
    public TestCoffeeShop()
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

    // Testing Netprofit calculation 
    @Test 
    public void  netprofitTest() { 
        float p = 8 ; // Estimated profit for serving each customer 
        float c= 900 ; // Cost for staffing each cashier 
        int s = 3; // Number of Cashiers
        int num_of_served = 1074 ;  //Number of customer served for the day 
        boolean calc = false ; // Used for asserting whether the_ans == tru_ans 

        CoffeeShop b = new CoffeeShop() ; // Creating an instance of coffee shop 

        float the_ans = b.calculateNetProfit(p,c,s,num_of_served); 
        float  tru_ans = ((p*num_of_served) - ((c*s))) ;

        if (the_ans - tru_ans ==0) {
            calc = true; 
        }

        assertTrue(calc);

    }

    // Testing Total Profit Calculation 
    @Test 
    public void totalprofitTest() { 
        float p = 8 ;
        int num_of_served = 1074 ;
        boolean calc = false ; 

        CoffeeShop b = new CoffeeShop() ; // Creating an instance of coffee shop 

        float the_ans = b.calculateTotalProfit(p, num_of_served);
        float tru_ans = p*num_of_served;

        if (the_ans - tru_ans ==0) {
            calc = true; 
        }

        assertTrue(calc);         
    } 

    // Testing the Total Cost calculation 
    @Test 
    public void totlalcostTest() { 
        float c= 900 ; // Cost for staffing each cashier 
        int s = 3; // Number of Cashiers   
        boolean calc = false; 

        CoffeeShop b = new CoffeeShop() ; // Creating an instance of coffee shop 

        float the_ans = b.calculateTotalCost(s,c); 
        float tru_ans = s*c ;

        if (the_ans - tru_ans ==0) {
            calc = true; 
        }

        assertTrue(calc); 
    }

    // Testing The Overflow Rate 
    @Test 
    public void overflowrateTest() { 

        int num_of_served = 859; 
        int overflow = 250 ; 
        boolean calc= false ;

        CoffeeShop b = new CoffeeShop() ; // Creating an instance of coffee shop 

        float the_ans= b.calculateOverFlow(num_of_served,overflow) ; 
        float tru_ans = ((float)overflow/(overflow+num_of_served))*100 ;

        if (the_ans - tru_ans ==0) {
            calc = true; 
        }

        assertTrue(calc); 

    }

    //Testing the maxWaitTime method
    @Test 
    public void maxWaitTimeTest() { 

        CoffeeShop a = new CoffeeShop() ; // Creating an instance of coffee shop
        ArrayList<Customer> arr = new ArrayList<Customer>() ; //Creating an arraylist to hold instances of 

        // Creating Instances of Customer 
        Customer b = new Customer (1600, 1); 
        Customer c = new Customer (2000,2); 
        Customer d = new Customer (3000,3); 
        
        //Setting Wait-Times
        b.setWaitTime(450); 
        c.setWaitTime(600); 
        d.setWaitTime(100); 
        
        //Adding to ArrayList of Customers 
        arr.add(b);
        arr.add(c);
        arr.add(d); 
        
        
        
        int the_ans = a.maxWaitTime(arr) ; 
        int tru_ans = 600;

        assertEquals(the_ans,tru_ans);

    }

    //Testing the Average Wait Time 
    @Test 
    public void avgWaitTimeTest() {
        CoffeeShop a = new CoffeeShop() ; // Creating an instance of coffee shop
        ArrayList<Customer> arr = new ArrayList<Customer>() ; //Creating an arraylist to hold instances of 

        // Creating Instances of Customer 
        Customer b = new Customer (1600, 1); 
        Customer c = new Customer (2000,2); 
        Customer d = new Customer (3000,3); 
        
        //Setting Wait-Times
        b.setWaitTime(450); 
        c.setWaitTime(600); 
        d.setWaitTime(100);
        
        //Adding to ArrayList of Customers 
        arr.add(b);
        arr.add(c);
        arr.add(d); 
        
        
        int the_ans = a.avgWaitTime(arr) ; 
        int tru_ans = 383;

        assertEquals(the_ans,tru_ans);
    }

    // Testing stringSplit method 
    @Test
    public void stringSplitTest(){ 
        CoffeeShop b = new CoffeeShop() ; // Creating an instance of coffee shop

        // String split 
        String test = "06:00:05 AM" ; 

        String [] the_ans = b.stringSplit(test) ; 

        String [] tru_ans= new String [] { "06" , "00", "05", "AM"} ; 

        assertArrayEquals(the_ans, tru_ans) ; 

    }

    //Testing the Time Conversion Method 
    @Test
    public void convertTimeTest(){ 
        CoffeeShop b = new CoffeeShop() ; // Creating an instance of coffee shop
        String test = "06:00:05 AM" ; // Creating a string from the file 
        String [] arr = b.stringSplit(test); // Array of split string 

        int the_ans = b.convertTime(arr) ; 
        int tru_ans = 21605 ; 

        assertEquals(the_ans,tru_ans); 

    }

    //Testing the Adding to Piority Que method 
    @Test
    public void addToPQTest() { 
        CoffeeShop a = new CoffeeShop() ; // Creating an instance of coffee shop
        // Creating Instances of Customer 
        Customer b = new Customer (7500, 1) ;
        Customer c = new Customer (4200, 2) ;
        Customer d= new Customer (2200, 3) ;
        Customer e = new Customer (5200, 4) ;
        Customer f = new Customer (1200, 5) ;
        //Creating Instances of Events
        Event g = new Event (b,7500,Event.ARRIVAL,1) ; 
        Event h = new Event (c,4200,Event.ARRIVAL,2) ; 
        Event i = new Event (d,2200,Event.ARRIVAL,3) ; 
        Event j = new Event (e,5200,Event.ARRIVAL,4) ; 
        Event k = new Event (f,1200,Event.ARRIVAL,5) ; 
        
        //Adding the events to the ProrityQue in CoffeShop 
        a.addToPQ(g); 
        a.addToPQ(h); 
        a.addToPQ(i); 
        a.addToPQ(j); 
        a.addToPQ(k);
        
        //If events are sucessfully added to the prority que, the event with the 
        // smallest time should be the first one removed
        Event the_ans = a.getPQ().remove(); 
        Event tru_ans = k ; 
        
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
