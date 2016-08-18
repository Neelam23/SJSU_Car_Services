package com.request;

import java.io.InputStreamReader;

import java.util.Scanner;
import java.util.Date;
import java.io.*;
import java.sql.SQLException;
import com.dao.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class SimpleRideRequest extends RideRequest{
	
	public int memId;
	public String pickUpLocation;
	public String dropOffLocation;
	public int vehicleType;
	public int numOfVehicle;
	public Date dateOfRide;
	public String email;
	MySQLDB db;
	
	
	public SimpleRideRequest(String email, MySQLDB db){
		this.email= email;
		this.db = db;
		
	}
	
	public void processRequest(){
    	try{
	        BufferedReader  input = new BufferedReader(new InputStreamReader(System.in));        
	        System.out.println("\nPlease fill in the information below: ");
	        
	        
	        System.out.print("Enter Pick up Location(sf,sj,mv,paloalto):  ");
	        pickUpLocation = input.readLine();
	        System.out.print("Enter Drop off Location(sf,sj,mv,paloalto):   ");
	        dropOffLocation = input.readLine();
	        int i= db.checkRoute(this);     //Check route   
		    if(i==1){
		        System.out.println("\nThis route is available!!");
		        
		        System.out.print("\nPlease Select Vehicle Type: ");
		        System.out.println("Please Select from Following options:\n"+"1. Sedan"+"\n2. SUV"+"\n3. Truck"+"\n4. Ferry"+"\n5. Luxury Car");
		        int val = Integer.parseInt(input.readLine());   
		        if( val==1 || val==2|| val==3||val==4||val==5){
		        	System.out.println("Vehicle type selected!!");
		        	vehicleType = val;
		        	
	//		        System.out.print("\nEnter Number of Vehicle you want: ");
	//		        numOfVehicle = Integer.parseInt(input.readLine());
			        
			        System.out.print("\nEnter Date of Ride in dd/MM/yyyy format: ");
			        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			        try{
				        dateOfRide = formatter.parse(input.readLine());
				        System.out.println(dateOfRide);
						} catch (ParseException e) {
							System.out.println("Wrong Date format or value\n");
							e.printStackTrace();
						}
		        	
		          }else{
		        	System.out.println("\n Wrong input!!");
		          }
		        
		    }else if(i==0){
		        System.out.println("\nSorry!! this Route is currently not available");      
		    }
	   }catch (IOException e) {
	            e.printStackTrace();
	   }
     		
	 db.saveRideRequest(this);
	}
  
}
