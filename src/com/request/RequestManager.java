package com.request;

import com.dao.MySQLDB;

public class RequestManager {
    private RideRequest rideReq;
    private ParkingRequest parkingReq;
    public String email;
        
    public RequestManager(String email){
      this.email= email;
      rideReq = new RideRequest(email);
      parkingReq = new ParkingRequest(email);
    }
        
    public void manageRide(){
    	rideReq.processRequest();
    }
    
    public void manageParking(){
    	parkingReq.processRequest();
    }
}
