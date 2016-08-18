package com.request;

import java.util.Scanner;

import com.dao.MySQLDB;
import com.rules.*;

public class RequestManager {
    private RideRequest rideReq;
    private ParkingRequest parkingReq;
    public String email;
    MySQLDB db;
        
    public RequestManager(String email, MySQLDB db){
      this.email= email;
      this.db= db;
      rideReq = new SimpleRideRequest(email, this.db);
      
      parkingReq = new ParkingRequest(email,this.db);
    }
        
    public void manageRide(){
    	System.out.println("Please Select from Following options:\n"+"1. Carpool Request"+"\n2. Line Request"+"\n3. Simple Request");
    	Scanner scanner = new Scanner(System.in);
		int selection = scanner.nextInt();
		
    	if(selection==1){
    		CarPoolRequest carpool = new CarPoolRequest(rideReq);
    		carpool.setRideType();
    		carpool.processRequest();
    		
    	}else if(selection==2){
    		LineRequest line = new LineRequest(rideReq);
    		line.setRideType();
    		line.processRequest();
    		
    	}else
    		System.out.println("This is simple ride request");
    		rideReq.processRequest();
    }
    
    public void manageParking(){
    	parkingReq.processRequest();
    }


    //rule related
    public void manageRideCancellation(){
    	RideRule RR= new RideRule();
    	RR.cancellationRule(email, db); //calling cancellation rule for ride
    }
    
    public void manangeParkingCanellation(){
    	ParkingRule PR= new ParkingRule();
    	PR.cancellationRule(email, db);  //calling cancellation rule for parking
    }

}
