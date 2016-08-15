package com.request;

import com.dao.MySQLDB;

public class RequestManager {
    private RideRequest rideReq;
    private ParkingRequest parkingReq;
    public String email;
    MySQLDB db;
        
    public RequestManager(String email, MySQLDB db){
      this.email= email;
      this.db= db;
      rideReq = new RideRequest(email, this.db);
      parkingReq = new ParkingRequest(email,this.db);
    }
        
    public void manageRide(){
    	rideReq.processRequest();
    }
    
    public void manageParking(){
    	parkingReq.processRequest();
    }
}
