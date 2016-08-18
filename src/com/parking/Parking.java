package com.parking;

public class Parking implements ParkingState {
    private ParkingInformation info;
    private int timeSpent = 1;
    
    public Parking(ParkingInformation ride){
        this.info= ride;
    }
    
    public void parkingTime(int id){
        System.out.println("Parking ID: " + id + ". The parking is in progress and tracking started");
    }
    public void systemClock(int id){
        System.out.println("Parking ID: " + id + ". The parking is in progress and half time has been over");
        if (timeSpent == 3)
        {
             info.setParkingState(new Parking_Finished(info));
        }
        timeSpent++;       
    }
    public void payment(int id){
        System.out.println("Parking ID: " + id + ". The parking is in progress and 25% of the total amount has been hold");
    }
    
}
