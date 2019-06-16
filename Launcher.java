
/**
 * Write a description of class Launcher here.
 *
 * @author (Irwin Frimpong)
 * @version (10/12/18)
 */

import java.util.*;

public class Launcher
{
    // Instance Variables 

    /**
     * Constructor for objects of class Launcher
     */
    public Launcher()
    {

    }

    public static void main (String[] args ) { 
        // Creating Simulation Instance of Launcher 
        Launcher sim = new Launcher(); 

        sim.run(); 
    } 

    public void run() { 
        // Creating new instance of coffee shop 
        CoffeeShop shop_sim = new CoffeeShop() ; 

        shop_sim.run() ; 
    }
}
