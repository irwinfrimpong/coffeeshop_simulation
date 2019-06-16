/**
 * Write a description of class CoffeeShop here.
 *
 * @author (Irwin Frimpong)
 * @version (10/12/18)
 */
import java.util.*;
import java.util.Scanner ;
import java.io.FileReader;
public class CoffeeShop
{
    // Instance Variables 

    // Variable will hold the number of cashiers which would be specified by the user
    private int num_of_s;  

    // Variable will hold number of available cahiser 
    private int s_available; 

    //Estimated profit for serving each customer 
    private float p ; 

    // Cost of staffing a cashier per day 
    private float c; 

    // Average time each cashier spends on each customer(in seconds) 
    private int t ; 

    // Number of customers served in a simulation
    private int num_of_served=0; 

    // Overflow
    private int overflow=0;

    // Variables to hold the metrics of the coffe shop 
    private float netprofit ; // Varibale holds the profit made in a day for the coffeeshop 
    private float totalcost; // Total cost of keeping the coffee shop open 
    private float totalprofit; // Variable holds the total profit 
    private float overflow_rate ; // Hold the overflow rate 

    private Integer max_wait_time; // Varaible to hold the max wait time 
    private int avg_wait_time ; // Varaible to hold the average wait time ; 

    // Prioity Queue of Events
    private PriorityQueue<Event> shop = new PriorityQueue<Event>() ;  

    // Queue of Customers In Line 
    private Queue<Event> line = new LinkedList<>() ; 

    // Arraylist to store the waitimes 
    private ArrayList<Customer> waittime = new ArrayList<Customer>(); 

    /**
     * Constructor for objects of class CoffeeShop
     */
    public CoffeeShop()
    {

    }
    
    
    public void run() {
        try { 

            // Asking the user to specify the number of cashiers they would like to have in this 
            // simulation 

            Scanner reader = new Scanner(System.in) ; 
            // Asking for num of strings
            System.out.println("Enter the number cashiers: "); 
            num_of_s= reader.nextInt();

            // Scanner for reading from file 
            Scanner sc = null; 
            sc = new Scanner( new FileReader("input.txt"));

            int line_count = 0; // Counter to determine what line the scanner is on 
            int cust_name= 1 ; // Variable Keekping Track of Customer name 
            while(sc.hasNextLine() ) {
                if(line_count==0) { 
                    p= Float.parseFloat(sc.nextLine()); 
                    System.out.println("p: "+ p );

                }
                else if (line_count==1) {
                    c= Float.parseFloat(sc.nextLine()); 
                    System.out.println("c: "+ c );

                } 
                else if (line_count == 2) { 
                    t= (int)Float.parseFloat(sc.nextLine());
                    System.out.println("t: "+ t );
                } 

                else if( line_count > 2 ){
                    //String holds the time in each line 
                    String time = sc.nextLine(); 
                    //Stroring the split values of time in an array
                    String time_a [] = stringSplit(time); 

                    // Creating an instance of event to be inserted in the Events Priority Que
                    Customer c = new Customer(convertTime(time_a), cust_name); 
                    Event e = new Event(c,convertTime(time_a),Event.ARRIVAL,cust_name); 
                    cust_name++; // Incrementing the customer name 
                    //Adding to Priority Que 
                    addToPQ(e);  

                    //System.out.println("Time : "+ e.time + "  " + "Customer: " + c.arr_time);
                }   

                //Increment Line Count 
                line_count ++ ; 
                //System.out.println(line_count); 

            }

            // Start of Simulation  

            s_available = num_of_s; // Number of Availabe cashiers  
            int current_time=0 ; // Varable used to keep track of customers times to calculate their metrics

            Event s = new Event(); //Creating a new instance of event that will be used for our simulation
            int wait_time; // Holds the waittime of the customers
            int depart_time ; //Holds the departime of the customer

            //While our PriorityQue of shop is not empty 
            while( !shop.isEmpty()) {
                s= shop.remove(); 
                //System.out.println("Time : "+ s.getTime() +  " Customer: " + s.getName() + " Type : "+ s.getType() );

                // First check if the customer arrival time is within tht bounds of the shop's 
                // opening and closing time

                if ((s.getTime() <= 79200 && s.getTime() >= 21600) || (s.getType() == Event.DEPARTURE)) { 

                    current_time= s.getTime();  
                    if (s.getType() == Event.ARRIVAL) { // If the event is an arrival type 
                        if (s_available >0) { //Checking if there are available cashiers 
                            s_available -- ; // Decrement the number of cashiers 
                            num_of_served ++ ; //Increase the number of people served 

                            //System.out.println( "Customer " + s.getName() + " is served" + " Num of A_s: " + s_available ); 

                            //Calculate Depature time 
                            depart_time = s.departTime(current_time,t);

                            // Wait time should be zero 
                            wait_time= 0 ; 

                            // Setting the event time equal to the departure time of that event
                            s.setTime(depart_time);
                            // Setting the customer customer departue time 
                            s.s.setDepartTime(depart_time); 

                            //Setting Customers Waittime 
                            s.s.setWaitTime(wait_time);

                            // Adding To The arraylist of customers
                            waittime.add(s.s);

                            // Changing the event type to a departure after it is served
                            s.setType(Event.DEPARTURE) ; 

                            addToPQ(s); // Adding the event back to the queue as a departure event

                            //System.out.println( "Customer " + s.getName() + " Departure Time: " + s.getTime()); 
                            //System.out.println( "Customer " + s.getName() + " Current Time: " + current_time ); 

                        } 

                        else { // If there are no cashiers available, we want to add the customer into a line 
                            // Checking if the line is full 
                            if (line.size() < (8*num_of_s)) {
                                line.add(s) ; 
                                //System.out.println("Linesize: " + line.size()); 

                            }
                            else  { // If the line is full, we dont add them to the line, increase overflow
                                overflow ++ ; // Increase overflow 
                                //System.out.println("Customer: " + s.getName() + " Time: " + s.getTime()+  " turned away"); 

                            }
                        }
                    }
                    else if (s.getType() == Event.DEPARTURE) { // If the event is a departure type
                      
                        //System.out.println( "Customer " + s.getName() + " leaves" + " Num of A_s: " + s_available );

                        //Get another person 
                        if (line.size() != 0) { 
                            // Remove From the Line Queue 
                            s = line.remove(); 

                            num_of_served ++ ; // Increase Number of served 
                            wait_time = s.waitTime(current_time,s.getTime());
                            // Printing Out Waittime 
                            //System.out.println ( "Customer: " + s.getName() + " Wait Time: " + wait_time); 

                            //Calculate Depature time 
                            depart_time = s.departTime(current_time,t);

                            // Setting the Event time to the departure time 
                            s.setTime( depart_time);
                            // Setting the customer customer departue time 
                            s.s.setDepartTime(depart_time); 

                            s.setType(Event.DEPARTURE) ; 
                            addToPQ(s); // Adding the event back to the queue as a departure event

                            //System.out.println( "Customer " + s.getName() + " is served" );
                            //System.out.println( "Customer " + s.getName() + " Departure Time: " + s.getTime()); 
                            //System.out.println( "Customer " + s.getName() + " Current Time: " + current_time );

                            //Setting Customers Waittime 
                            s.s.setWaitTime(wait_time); 

                            //Adding the waittime to the Arraylist of Waitime 
                            waittime.add(s.s); 

                        }
                        else {
                            s_available ++; 
                        } 
                    }

                }
                else { 
                    System.out.println( "Customer " + s.getName() + " is turned away ");
                }

            }
            //System.out.println ("Num of Overflow: " + overflow); 
            //System.out.println ("Num of Customers Served  " + num_of_served); 
            //System.out.println ("Number of Available Cashier: " + s_available ); 
            
            CoffeeShop coffee = new CoffeeShop(); 
            
            System.out.println ("\nTotal Profit: " + coffee.calculateTotalProfit(p, num_of_served) ); 
            System.out.println ("Total Cost: " + coffee.calculateTotalCost(num_of_s,c));
            System.out.println ("Netprofit: " + coffee.calculateNetProfit(p ,c, num_of_s,num_of_served) ); 
            System.out.println ("Average Wait-time: " + coffee.avgWaitTime(waittime) );
            System.out.println ("Max Wait-Time: " + coffee.maxWaitTime(waittime)); 
            System.out.printf("Over-Flow Rate In Percent: %.2f", coffee.calculateOverFlow(num_of_served,overflow)); 

        }
        catch (Exception e) {
            System.out.println ("Exception occured " + e); 

        }
    }

    /**
     * stringSplit returns an array that has the arrival time of the customer
     * split into the indexes of the array  
     * 
     * @param String time
     * @return String [] time_a
     * 
     */
    public String [] stringSplit (String time) {

        //Stroring the split values of time in an array
        String time_a [] = time.split(":| "); 

        return time_a ; 
    }

    /**
     * convertTime returns the time arrival time of the customer in seconds 
     * 
     * @param String [] time_a
     * @return int time_conv
     * 
     */
    public int convertTime( String[] time_a ) { 
        int hours_to_sec = (int)Float.parseFloat(time_a[0]) * 3600 ; 
        int mins_to_sec = (int)Float.parseFloat(time_a[1]) * 60 ; 
        int sec = (int)Float.parseFloat(time_a[2]) ; 

        // Calculating the time to seconds

        int time_conv = hours_to_sec + mins_to_sec + sec; 

        //Returning the converted time 
        return time_conv;
    }

    /**
     * addToPQ methods adds an instance of event to Priority Queue
     * 
     * @param Event e 
     */
    public void addToPQ(Event e ) {
        // Adds Instance of Event Passed Into The Prp
        shop.add(e) ; 

    }

    /**
     * getPQ methods returns the priority que
     * 
     * @return ProrityQueue<Event> shop ;  
     */
    public PriorityQueue<Event> getPQ() { 
        return shop ; 
    }

    /**
     * calculateNetProfit calulates profit made for the day in the coffee shop 
     * 
     * @param flaot p @param float c , int s , int num_of_served 
     * @return netprofit 
     * 
     */
    public float calculateNetProfit(float p , float c, int s, int num_of_served)
    {
        netprofit = (p*num_of_served) - ((c*s)) ; 
        return netprofit ; 

    }

    /**
     * calculateTotalProfit calulates the total profit 
     * 
     * @param flaot p @param float c , int s , int num_of_served 
     * @return totalprofit 
     * 
     */
    public float calculateTotalProfit ( float p , int num_of_served) { 
        totalprofit = p*num_of_served; 

        return totalprofit; 
    }

    /**
     * calculateTotalCost calulates the total cost 
     * 
     * @param flaot p @param int s , @param float c
     * @return totalcost 
     * 
     */
    public float calculateTotalCost ( int s, float c)  {
        totalcost = s*c ;
        return totalcost; 
    } 

    /**
     * calculateOverFlow calulates the the overflow rate of the coffee shop
     * 
     * @param int num_of_served @param int overflow
     * @return overflow_rate
     * 
     */
    public float calculateOverFlow ( int num_of_served, int overflow)  {

        overflow_rate = ((float)(overflow)/(overflow + num_of_served))*100  ; 

        return overflow_rate ; 
    }

    /**
     * maxWaitTime returns the max waiting time from the arraylist of waitimes 
     * 
     * @param ArrayList <Customer> waittime
     * @return max_wait_time
     * 
     */
    public int maxWaitTime( ArrayList<Customer> waittime ) { 

        int max_wait_time = 0 ; 

        // Perform a linear search of the arraylist of customers to find the largest wait time
        for (int i=0; i<waittime.size()-1; i++) {

            if(max_wait_time < waittime.get(i).getWaitTime()) {

                max_wait_time = (waittime.get(i)).getWaitTime();
            }
        }
        return max_wait_time;

    } 

    /**
     * avgWaitTime returns the average waittime 
     * 
     * @param ArrayList <Customer> waittime
     * @return avg_wait_time
     * 
     */
    public int avgWaitTime( ArrayList<Customer> waittime ) { 
        int total = 0; 
        for ( int i =0 ; i<waittime.size() ; i++) { 
            total +=  (waittime.get(i)).getWaitTime();
        }

        avg_wait_time = total / waittime.size() ; 

        return avg_wait_time ; 
    }
}