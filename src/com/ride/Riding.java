package com.ride;

public class Riding implements RideState {
    private RideInformation info;
    
    public Riding(RideInformation ride){
        this.info= ride;
    }
    
    public void pickUpAndDropOffLocation(){
        System.out.println("The ride is in riding state and pick up is done");
    }
    public void gpsLocation(){
        System.out.println("The ride is in riding state");
        info.setRideState(new Ride_Finished(info));
    }
    public void payment(){
        System.out.println("The ride is in riding state and 25% of the total amount has been hold");
    }
    
}
