package com.parking;


public class Parking_Not_Started implements ParkingState {
    private ParkingInformation info;
    
    public Parking_Not_Started(ParkingInformation r){
        this.info = r;
    }
        
    public void parkingTime(){
        System.out.println("Parking Time");
        info.setParkingState(new Parking(info));
    }
    public void gpsLocation(){
        System.out.println("Parking Location");
    }
    public void payment(){
        System.out.println("The parkign has not started yet");
    }
}
