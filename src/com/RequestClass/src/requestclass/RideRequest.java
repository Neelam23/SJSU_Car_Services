package requestclass;

import java.util.Scanner;

public class RideRequest implements RequestInterface {
    private int memId;
    private String pickUpLocation;
    private String dropOffLocation;
    private int numOfSeats;
    private int numOfCars;
    
  
    public void processRequest(){          
        Scanner input = new Scanner(System.in);        
        System.out.println("Please fill in the information below: ");
        System.out.print("Pick up Location: ");
        pickUpLocation = input.next();
        System.out.print("Drop off Location: ");
        dropOffLocation = input.next();
        System.out.print("Number of Cars: ");
        numOfCars = input.nextInt();
        System.out.print("Number of seats per car: ");
        numOfSeats = input.nextInt();
        System.out.println("this is ride class");
    }
}
