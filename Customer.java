
/**
 * Write a description of class Customer here.
 *
 * @author (Irwin Frimpong)
 * @version (10/12/18)
 */
public class Customer  
{
    // instance variables - replace the example below with your own

    private  int arr_time; // Holds the time in which the customer arrives
    private  int wait_time=0; // Holds the wait time of each customer 
    private  int depart_time; // Holds the departure time of each customer 
    private  int name ; 

    /**
     * Constructor for objects of class Customer
     */
    public Customer( int arr_time, int name )
    {
        this.arr_time = arr_time ; 
        this.name = name; 

    }

    // Getter and getters in customer class 

    /**
     *  setDeparture method sets thhe departure time for each customer 
     *
     * @param int d_time 
     *
     */
    public void setDepartTime(int d_time)
    {
        // Setting the departure time 
        depart_time = d_time;       

    }

    /**
     *  setWaittime method sets thhe wait time for each customer 
     *
     * @param int w_time
     *
     */
    public void setWaitTime(int w_time)
    {
        // Setting the waitime 
        wait_time = w_time; 

    }

    /**
     *  getWaittime method gets the wait time for each customer 
     *
     * @return int wait_time
     *
     */
    public int getWaitTime()
    {
        return wait_time; 

    }
    
} 
