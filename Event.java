
/**
 * Event class implements comparable which arranges the events by the time 
 * in which they come in 
 * 
 * @author (Irwin Frimpong)
 * @version (a version number or a date)
 */
public class Event implements Comparable <Event> 
{
    // instance variables - replace the example below with your own

    static final int ARRIVAL = 0 ; 
    static final int DEPARTURE = 1 ;

    private  int type;  // Holds the type of event 
    private int  time; // Holds the time in which the event occurs
    private int depart_time; // Holds the departure time 
    private int wait_time; // Holds the waittime 
    private int name ; // Holds the name of the customer

    // // Instance of customer with time passed
    Customer s = new Customer(time,name); 

    /**
     * Constructor for objects of class Event
     */
    public Event () { 
    }

    public Event(Customer s , int time, int what, int name )
    {
        this.s=s; 
        this.time= time; 
        this.type= what; 
        this.name = name ; 

    }

    /**
     * compareTo method compares one instance of event to another
     *  @param Event test 
     */
    public int compareTo(Event test)
    {
        // Comparing the two times of the events 
        int comp=  this.time - test.time ; 
        // Variable storing comparator value 
        int val= 0; 

        if (comp < 0) { // Less than 
            val =-1; 
        } 
        else if (comp >0) { // Greater than 
            val= 1 ; 
        } 
        else if (comp == 0 ){ // Equal To 
            val=  0 ; 
        } 
        return val ; 
    }

    /**
     *  setTime method sets the time of the event in the CoffeeShop
     *
     * @param int set_time
     */
    public void setTime(int set_time) 
    { 
        //Setting event's time to the time value passed into the method 
        time= set_time ; 
    } 

    /**
     *  getTime method gets time of the event in the CoffeeShop
     *
     * @return time
     */
    public int getTime() { 
        return time ; 
    } 

    /**
     *  getName method returns the name of the Event 
     *
     * @return name
     */
    public int getName() { 
        return name ; 
    } 

    /**
     *  getType method returns the type of the Event 
     *
     * @return type
     */
    public int getType() { 
        return type ; 
    } 

    /**
     *  setType method sets the type of the Event 
     *
     * @param int set_type
     */
    public void setType(int set_type) { 
        // Changing the type of the event to type passed into the method
        type = set_type ; 
    } 

    /**
     *  departTime method calculates the time in which the customer leaves the 
     *  coffee shop
     *
     * @param int arr_time @param int wait_time @param int time_served 
     * @return    depart_time
     */
    public int departTime(int current_time,int time_served)
    {
        // Calculating departure time 
        depart_time = current_time + time_served ; 

        return depart_time ;       

    }

    /**
     *  waitTime method calculates the time in which the customer leaves the 
     *  coffee shop
     *
     * @param int arr_time @param int wait_time @param int time_served 
     * @return    wait
     */
    public int waitTime(int current_time, int arr_time) { 

        wait_time = current_time - arr_time;  

        return wait_time;         
    }

}
