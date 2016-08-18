package com.ride;


public class Ride_Not_Started implements RideState {
    private RideInformation info;
    
    public Ride_Not_Started(RideInformation r){
        this.info = r;
    }
        
    public void pickUpAndDropOffLocation(int id){
        System.out.println("Ride ID: " + id +". The ride request has been received.");
        info.setRideState(new Riding(info));
    }
    public void gpsLocation(int id){
        System.out.println("Ride ID: " + id + ". The ride has not started yet.");
    }
    public void payment(int id){
        System.out.println("Ride ID: " + id + ". The ride has not started yet.");
    }
}
