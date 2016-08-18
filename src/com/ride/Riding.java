package com.ride;

public class Riding implements RideState {
    private RideInformation info;
    private int zone = 1;
    
    public Riding(RideInformation ride){
        this.info= ride;
    }
    
    public void pickUpAndDropOffLocation(int id){
        System.out.println("Ride ID: " + id + ". The ride is in progress and pick up is done");
    }
    public void gpsLocation(int id){
        System.out.println("Ride ID: " + id + ". The ride is in progress and in zone " + zone);
        if (zone == 3)
        {
            info.setRideState(new Ride_Finished(info));
        }
        zone++;
    }
    public void payment(int id){
        System.out.println("Ride ID: " + id + ". The ride is in progress and 25% of the total amount has been hold");
    }
    
}
