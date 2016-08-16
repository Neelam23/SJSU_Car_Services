package com.parking;

public class Parking_Finished implements ParkingState{
    private ParkingInformation info;
    
    public Parking_Finished(ParkingInformation r){
         this.info = r;
    }
    
    public void parkingTime(){
        System.out.println("Parking time");
    }
    public void gpsLocation(){
        System.out.println("GPS Location");
    }
    public void payment(){
        System.out.println("The parking has been finished and you have been charged");
    }  
}
