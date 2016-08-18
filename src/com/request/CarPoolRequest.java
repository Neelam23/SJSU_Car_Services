package com.request;

public class CarPoolRequest extends RequestDecorator{
	
	public CarPoolRequest(RideRequest rideReq){
		super(rideReq);
	}
	
	public void setRideType(){
		RideRequest.setRideRequest("Carpool Ride Request");
	}
    
	public void processRequest(){
		super.processRequest();
	}
}
