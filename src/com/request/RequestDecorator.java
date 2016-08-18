package com.request;

public abstract class RequestDecorator extends RideRequest{

	protected  RideRequest RR;
	
	public RequestDecorator(RideRequest RR){
		this.RR= RR;
	}
	
	public void processRequest(){
		RR.processRequest();
	}
}
