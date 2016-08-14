package com.request;

import java.util.Scanner;

public class ParkingRequest implements RequestInterface{
    private int driverId;
    private String parkingLocation;
    private int numOfParkingSpots;
    private int numOfHours;
    public String email;
    
    public ParkingRequest(String email){
        this.email= email;
      }
   
    
    public void processRequest(){
        Scanner input = new Scanner(System.in);        
        System.out.println("Please fill in the information below: ");
        System.out.print("Parking Location: ");
        parkingLocation = input.next();
        System.out.print("Number of parking spots: ");
        numOfParkingSpots = input.nextInt();
        System.out.print("Number of hours you want to book parking spot for: ");
        numOfHours = input.nextInt();
        System.out.println("this is parking class");
    }
}
