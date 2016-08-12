package com.ride;

public class Ride_Finished implements RideState{
    private RideInformation info;
    
    public Ride_Finished(RideInformation r){
         this.info = r;
    }
    
    public void pickUpAndDropOffLocation(){
        System.out.println("The ride has been finished");
    }
    public void gpsLocation(){
        System.out.println("The ride has been finished");
    }
    public void payment(){
        System.out.println("The ride has been finished and you have been charged");
    }  
}
