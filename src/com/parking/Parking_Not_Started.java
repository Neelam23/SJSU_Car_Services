package com.parking;


public class Parking_Not_Started implements ParkingState {
    private ParkingInformation info;
    
    public Parking_Not_Started(ParkingInformation r){
        this.info = r;
    }
        
    public void parkingTime(int id){
         System.out.println("Parking ID: " + id +". The parking request has been received.");
        info.setParkingState(new Parking(info));
    }
    public void systemClock(int id){
        System.out.println("Parking ID: " + id +". The parking hasn't started yet.");
    }
    public void payment(int id){
        System.out.println("Parking ID: " + id +". The parking hasn't started yet.");
    }
}
