package com.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import com.dao.MySQLDB;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ParkingRequest implements RequestInterface{
    private int driverId;
    private String parkingLocation;
    private int numOfParkingSpots;
    private int numOfHours;
    public String email;
    public Date dateOfParking;
    MySQLDB db;
    
    public ParkingRequest(String email,MySQLDB db){
        this.email= email;
        this.db= db;
      }
   
    
    public void processRequest(){
//        Scanner input = new Scanner(System.in);        
//        System.out.println("Please fill in the information below: ");
//        System.out.print("Parking Location: ");
//        parkingLocation = input.next();
//        System.out.print("Number of parking spots: ");
//        numOfParkingSpots = input.nextInt();
//        System.out.print("Number of hours you want to book parking spot for: ");
//        numOfHours = input.nextInt();
//        System.out.println("this is parking class");
        
        try{
	        BufferedReader  input = new BufferedReader(new InputStreamReader(System.in));        
	        System.out.println("\nPlease fill in the information below: ");
	        
	        
	        System.out.print("Enter Parking Location(sf, sj, la):  ");
	        parkingLocation = input.readLine();
//	        System.out.print("Enter Drop off Location(sf, sj, la):  ");
//	        dropOffLocation = input.readLine();
//	        int i= db.checkRoute(this);     //Check route   
//		    if(i==1){
//		        System.out.println("\nThis route is available!!");
//		    }else if(i==0){
//		        System.out.println("\nSorry!! this Route is currently not available");
//		    }
//	        
		    
	        System.out.print("\nSelect Number of parking spots: ");
	        numOfParkingSpots = Integer.parseInt(input.readLine());
	        
	        
	        System.out.print("\nEnter Number of hours you want to book parking spot for:");
	        numOfHours = Integer.parseInt(input.readLine());
	        
	        System.out.print("\nEnter Date of Parking in dd/MM/yyyy format: ");
	        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        try{
		        dateOfParking = formatter.parse(input.readLine());
		        System.out.println(dateOfParking);
				} catch (ParseException e) {
					System.out.println("Wrong Date format or value\n");
					e.printStackTrace();
				}
	        
	    	}catch (IOException e) {
	            e.printStackTrace();
	        }
    	
            db.saveParkingRequest(this);
    	}   
        
}
