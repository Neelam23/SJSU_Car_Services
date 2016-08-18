package com.request;

public class LineRequest extends RequestDecorator{

	public LineRequest(RideRequest rideReq){
		super(rideReq);
	}
	
	public void setRideType(){
		RideRequest.setRideRequest("Line Ride Request");
	}
    
	public void processRequest(){
		super.processRequest();
	}
}
