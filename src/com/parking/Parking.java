package com.parking;

public class Parking implements ParkingState {
    private ParkingInformation info;
    
    public Parking(ParkingInformation ride){
        this.info= ride;
    }
    
    public void parkingTime(){
        System.out.println("Parking time");
    }
    public void gpsLocation(){
        System.out.println("Parking Location");
        info.setParkingState(new Parking_Finished(info));
    }
    public void payment(){
        System.out.println("The parking is in progress and 25% of the total amount has been hold");
    }
    
}
