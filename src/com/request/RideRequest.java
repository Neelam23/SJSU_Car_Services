package com.request;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.sql.SQLException;
import com.dao.*;


public class RideRequest implements RequestInterface {
	
	public int memId;
	public String pickUpLocation;
	public String dropOffLocation;
	public int vehicleType;
	public int numOfVehicle;
	public String email;
	
	
	public RideRequest(){}
    
    public RideRequest(String email){
        this.email= email;
      }
  
    public void processRequest(){   
    	
    	RideRequest rideRequest= new RideRequest();
    	try{
	        BufferedReader  input = new BufferedReader(new InputStreamReader(System.in));        
	        System.out.println("Please fill in the information below: ");
	        System.out.print("Pick up Location: ");
	        rideRequest.pickUpLocation = input.readLine();
	        
	        System.out.print("Drop off Location: ");
	        rideRequest.dropOffLocation = input.readLine();
	        
	        System.out.print("Select Vehicle Type: ");
	        System.out.println("Please Select from Following options:\n"+"1. Sedan"+"\n2. SUV"+"\n3. Truck"+"\n4. Ferry"+"\n5. Luxury Car");
	        
	        rideRequest.vehicleType = Integer.parseInt(input.readLine());
	        
	        System.out.print("Number of Vehicle: ");
	        rideRequest.numOfVehicle = Integer.parseInt(input.readLine());
	        
	       //if route is availbe 
	        System.out.println("Request Received");
	    	}catch (IOException e) {
	            e.printStackTrace();
	        }
    	
    	MySQLDB db= new MySQLDB();
    	db.sendRideRequest(rideRequest);
    	
    	
    	}  //process request ends
    
}
