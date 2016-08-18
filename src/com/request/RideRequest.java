package com.request;


public abstract class  RideRequest implements RequestInterface { 
    
	private String rideType;
	
    public static void setRideRequest(String rideTY){
        System.out.println("this is a "+rideTY);
      }

    public abstract void processRequest();
    
}


