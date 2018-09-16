package cscie55.hw1.elevator;

/******************************
* 
* name: Brendan Murphy
* CSCIE-55 HW 1
* date: 09/16/2018
*
* Defines an elevator class that traverses a building and drops off passengers on their requested floors
******************************/

public class Elevator {

    /******************************
    * Requirement: Define a constant for the number of floors in the building, and set it to 7
    ******************************/
    
    static final int FLOOR_COUNT = 7;

    /******************************
    * Requirement: Define a field for tracking the Elevator's current floor 
    ******************************/
    
    private int currentFloor;

    /******************************
    * Requirement: Define a field for tracking the Elevator's direction of travel
    ******************************/ 
    
    private boolean goingUp;

    /******************************
    * Requirement: Define an array-valued field for tracking, for each floor, the number of passengers destined for 
    * that floor
    ******************************/ 
    
    private int[] passengersToFloor;

    
    private int numPassengers;     

    /******************************
    * Requirement: No-argument constructor that sets up the elevator's state
    ******************************/
    
    public Elevator(){
        currentFloor = 1;
        goingUp = true;
        numPassengers = 0;
        passengersToFloor = new int[FLOOR_COUNT+1];
    }
    
    /******************************
    * Requirement: Define a move() method which, when called, modifies the Elevator's state
    ******************************/   
    
    public void move(){
        
        if (goingUp == true) {
            //increments the current floor
            currentFloor++;
            //modifies the direction of travel
            if (currentFloor == FLOOR_COUNT) {
                goingUp = false;
                }
        } else {
            //decrements the current floor
            currentFloor--;
            //modifies the direction of travel
            if (currentFloor == 1) {
                goingUp = true;
	    }
    }
        //Clears the array entry tracking the number of passengers destined for the floor that the elevator 
        //has just arrived at
        numPassengers = (numPassengers - passengersToFloor[currentFloor-1]);
        passengersToFloor[currentFloor-1] = 0;
        
        //prints out the status of the elevator
        this.toString();
    }

    /******************************
    * Requirement: This method adds to the Elevator one passenger destined for the indicated floor
    * @param destinationFloor an integer indicating the destination of the incoming passenger
    ******************************/ 

    public void boardPassenger(int destinationFloor){
        passengersToFloor[destinationFloor - 1]++;
        numPassengers++;
    }

    /******************************
    * Requirement: indicate the number of passengers on board, and the current floor
    ******************************/ 
    
    public String toString()  {
        if (numPassengers == 1)
    {
        return "Floor " + Integer.toString(currentFloor) + ": " + Integer.toString(numPassengers) + " passenger";
    }
        else
    {
        return "Floor " + Integer.toString(currentFloor) + ": " + Integer.toString(numPassengers) + " passengers";
    }
    }


}
