package cscie55.hw2test;

import cscie55.hw2.Building;
import cscie55.hw2.Elevator;
import cscie55.hw2.ElevatorFullException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/*******************************************************************************************************
 *
 * name: Brendan Murphy
 * CSCIE-55 HW 2
 * date: 09/25/2018
 *
* Defines an elevator test class that tests the elevator class
 *******************************************************************************************************/

public class ElevatorTest
{
    // Don't board any passengers. Just check that the elevator moves up and down correctly.
    @Test
    public void elevatorMotion()
    {
        System.out.println("***** STARTING MOVE TEST *****");
        Building building = new Building();
        Elevator elevator = building.getElevator();
        int expectedFloorNumber = 1;
        // Go to the top
        while (expectedFloorNumber < Building.FLOORS) {
            checkElevator(elevator, expectedFloorNumber, 0);
            elevator.move();
            expectedFloorNumber++;
            checkElevator(elevator, expectedFloorNumber, 0);
        }
        assertEquals(Building.FLOORS, expectedFloorNumber);
        // Go back to the bottom
        while (expectedFloorNumber > 1) {
            checkElevator(elevator, expectedFloorNumber, 0);
            elevator.move();
            expectedFloorNumber--;
            checkElevator(elevator, expectedFloorNumber, 0);
        }
        assertEquals(1, expectedFloorNumber);
        System.out.println("***** MOVE TEST COMPLETED *****");
    }
    
    // Check that passengers get on and off correctly.
    @Test
    public void disembark() throws ElevatorFullException
    {
        System.out.println("***** STARTING DISEMBARK TEST *****");
        Building building = new Building();
        Elevator elevator = building.getElevator();
        checkElevator(elevator, 1, 0);
        // Add passengers and disembark them on the way up
        elevator.boardPassenger(3);
        elevator.boardPassenger(4);
        elevator.boardPassenger(4);
        elevator.boardPassenger(6);
        elevator.boardPassenger(6);
        elevator.boardPassenger(6);
        checkElevator(elevator, 1, 6);
        elevator.move();
        checkElevator(elevator, 2, 6);
        elevator.move();
        checkElevator(elevator, 3, 5);
        elevator.move();
        checkElevator(elevator, 4, 3);
        elevator.move();
        checkElevator(elevator, 5, 3);
        elevator.move();
        checkElevator(elevator, 6, 0);
        elevator.move();
        checkElevator(elevator, 7, 0);
        System.out.println("***** DISEMBARK TEST COMPLETED *****");
    }
    
    // Check that passengers on higher floors can call and board the elevator, and then
    // disembark on the ground floor.
    @Test
    public void call()
    {
        System.out.println("***** STARTING HIGHER FLOORS TEST *****");
        Building building = new Building();
        Elevator elevator = building.getElevator();
        building.getFloor(3).waitForElevator();
        building.getFloor(3).waitForElevator();
        building.getFloor(6).waitForElevator();
        building.getFloor(6).waitForElevator();
        building.getFloor(6).waitForElevator();
        checkElevator(elevator, 1, 0);
        elevator.move();checkElevator(elevator, 2, 0);
        elevator.move();checkElevator(elevator, 3, 2);
        elevator.move();checkElevator(elevator, 4, 2);
        elevator.move();checkElevator(elevator, 5, 2);
        elevator.move();checkElevator(elevator, 6, 5);
        elevator.move();checkElevator(elevator, 7, 5);
        elevator.move();checkElevator(elevator, 6, 5);
        elevator.move();checkElevator(elevator, 5, 5);
        elevator.move();checkElevator(elevator, 4, 5);
        elevator.move();checkElevator(elevator, 3, 5);
        elevator.move();checkElevator(elevator, 2, 5);
        elevator.move();checkElevator(elevator, 1, 0);
        System.out.println("***** HIGHER FLOORS TEST COMPLETED *****");
    }

    // Check handling of a full elevator.
    @Test
    public void elevatorFull()
    {
        System.out.println("***** STARTING ELEVATOR FULL TEST *****");
        Building building = new Building();
        Elevator elevator = building.getElevator();
        // Have enough people waiting on the 4th floor to exceed elevator capacity by 50%
        int waiting = (int) (Elevator.CAPACITY * 1.5);
        for (int i = 0; i < waiting; i++) {
            building.getFloor(4).waitForElevator();
        }
        // Move to 4, checking state
        elevator.move();
        checkElevator(elevator, 2, 0);
        elevator.move();
        checkElevator(elevator, 3, 0);
        elevator.move();
        // Should have filled the elevator, leaving people on 4
        checkElevator(elevator, 4, Elevator.CAPACITY);
        assertEquals(waiting - Elevator.CAPACITY, building.getFloor(4).getPassengersWaiting());
        // Get to the ground floor
        while (elevator.getCurrentFloor() != 1) {
            elevator.move();
            if (elevator.getCurrentFloor() == 1) {
                checkElevator(elevator, elevator.getCurrentFloor(), 0);
            } else {
                checkElevator(elevator, elevator.getCurrentFloor(), Elevator.CAPACITY);
            }
        }
        // Go back to 4
        while (elevator.getCurrentFloor() != 4) {
            elevator.move();
            if (elevator.getCurrentFloor() == 4) {
                // Check to see that the remaining passengers boarded
                checkElevator(elevator, elevator.getCurrentFloor(), waiting - Elevator.CAPACITY);
                assertEquals(0, building.getFloor(4).getPassengersWaiting());
            } else {
                checkElevator(elevator, elevator.getCurrentFloor(), 0);
            }
        }
        System.out.println("***** ELEVATOR FULL TEST COMPLETED *****");
    }
 
    private void checkElevator(Elevator elevator, int floorNumber, int passengers)
        {
            System.out.println("elevator is expected at: Floor " + floorNumber + ", elevator is at: Floor " + elevator.getCurrentFloor());
            System.out.println();
            assertEquals(floorNumber, elevator.getCurrentFloor());
            System.out.println("elevator expected to contain: " + passengers + " passengers, elevator contains: " + elevator.getPassengers() + " passengers");
            assertEquals(passengers, elevator.getPassengers());
          }
    }
