package com.ride;


public class Ride_Not_Started implements RideState {
    private RideInformation info;
    
    public Ride_Not_Started(RideInformation r){
        this.info = r;
    }
        
    public void pickUpAndDropOffLocation(){
        System.out.println("The ride has not started yet");
        info.setRideState(new Riding(info));
    }
    public void gpsLocation(){
        System.out.println("The ride has not started yet");
    }
    public void payment(){
        System.out.println("The ride has not started yet");
    }
}
